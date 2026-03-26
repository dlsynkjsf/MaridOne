# Payroll Regression Bruno Pack

This collection uses only the current API surface. It does not need any Java changes, but it does assume the backend is running in the `dev` profile so the seeded sample rows and `/api/test/payroll-run` endpoint are available.

## Before you start

- Start the backend and make sure the API is reachable at `http://localhost:8080`.
- Open the `local` environment in Bruno.
- If your dev database already had employees before this change, clear or recreate that dev data first, then restart the backend so `SampleRows` reseeds. The new Employee 6 case is only inserted when the employee table starts empty.
- The default seeded accounts are:
  - HR: `userName2` / `test`
  - Employee 6: `userName6` / `test`
  - Employee 10: `userName10` / `test`

If your dev database was not freshly seeded, run the `Get ... Account` requests first and use the returned `employeeId` values instead of the default `6` and `10`.

## First case to test

Use the Employee 6 seeded all-around payroll regression first. It now covers both payroll fixes in one cutoff:

- an approved OT on `2026-04-06 18:00-20:00` with no attendance overlap, which must not be paid
- a separate approved night OT on `2026-04-07 22:00-00:00` with real overlapping attendance, which must be paid and must produce a separate OT-backed night differential line

Run these requests in order:

1. `01 Login HR`
2. `02 Login Employee 6`
3. `03 Get Employee 6 Account`
4. `04 Get Employee 6 Overtime`
5. `05 Run Payroll For Employee 6 Window`
6. `06 Get Employee 6 Payroll Items`
7. `07 Get Employee 6 Earnings`
8. `08 Get Employee 6 Deductions`
9. `09 Get Employee 6 Attendance`

What to expect in `07 Get Employee 6 Earnings`:

- exactly one paid overtime line
- that overtime line should be about:
  - `hours = 2.00`
  - `amount = 1201.92307750`
- one separate non-overtime line should exist for the OT-backed night differential with about:
  - `hours = 2.00`
  - `amount = 120.192307750`
- there should not be any second overtime line for the approved but unworked OT on `2026-04-06`

What to expect in the other responses:

- `04` should show two approved overtime requests for Employee 6:
  - `2026-04-06 18:00-20:00`
  - `2026-04-07 22:00-00:00`
- `08` should show the normal statutory deductions and at least one `ABSENT_DEDUCTION`
- `09` should show the supporting attendance, including the overnight `2026-04-07 14:00` to `2026-04-08 00:00` session

## Other scenarios

### Scenario A: seeded all-around payroll regression

Run these requests in order:

1. `01 Login HR`
2. `02 Login Employee 6`
3. `03 Get Employee 6 Account`
4. `04 Get Employee 6 Overtime`
5. `05 Run Payroll For Employee 6 Window`
6. `06 Get Employee 6 Payroll Items`
7. Set `employee6_item_id` to the `id` of the item whose `runDetails.periodStart` is `2026-03-26` and whose `runDetails.periodEnd` is `2026-04-10`
8. `07 Get Employee 6 Earnings`
9. `08 Get Employee 6 Deductions`
10. `09 Get Employee 6 Attendance`

What to expect:

- `04` should show both approved overtime requests on `2026-04-06` and `2026-04-07`.
- `07` should show one paid overtime line only, for the night OT with attendance overlap.
- `07` should also show one separate non-overtime line for the OT-backed night differential.
- `08` should show normal statutory deductions and at least one `ABSENT_DEDUCTION`.
- This same cutoff also exercises normal payroll behavior around attendance, approved leave on `2026-04-08`, and the seeded holiday overrides on `2026-04-03` and `2026-04-09`.

### Scenario B: seeded night-differential regression

Run these requests in order:

1. `01 Login HR`
2. `10 Login Employee 10`
3. `11 Get Employee 10 Account`
4. `12 Get Employee 10 Overtime`
5. `13 Run Payroll For Employee 10 Window`
6. `14 Get Employee 10 Payroll Items`
7. Set `employee10_item_id` to the `id` of the item whose `runDetails.periodStart` is `2026-10-11` and whose `runDetails.periodEnd` is `2026-10-25`
8. `15 Get Employee 10 Earnings`
9. `16 Get Employee 10 Deductions`
10. `17 Get Employee 10 Attendance`

What to expect:

- `12` should show an approved overtime request on `2026-10-22` from `18:00` to `20:00`.
- `17` should show seeded overnight attendance on `2026-10-20` from `21:00` to `06:00`, which gives you a visible night differential case in this cutoff.
- `15` should show a positive-hour non-overtime earnings line for the overnight night differential.
- `15` should again show **no** paid overtime line for the `2026-10-22` request, because that approved OT also has no attendance overlap.
- `16` should show the usual statutory deductions, and you may also see attendance deductions because this window includes a short day and a late arrival.

## What this pack cannot fully automate through the current API

The compounded night-OT case needs a time window where approved overtime overlaps actual attendance between `22:00` and `06:00` Asia/Manila time. The current public API only creates attendance at `Instant.now()`, so Bruno cannot deterministically backdate that scenario during daytime.

For that reason, this collection includes an **optional live path** under requests `18` to `25`. Use it only if you want to try the compounded case in real time.

## Optional live night-OT path

Use this only when you can run the attendance calls in real time around the OT window. Before running it:

- Update these environment values to the exact Manila dates you want to test:
  - `live_work_date`
  - `live_period_start`
  - `live_period_end`
  - `live_overtime_start`
  - `live_overtime_end`
- The default example values use `2026-03-22` and `2026-03-23`. Change them before use.

Run these requests in order:

1. `01 Login HR`
2. `10 Login Employee 10`
3. Copy `employee10_access_token` into `live_employee_access_token` if the script did not do it for you
4. `18 Create Live Night OT Request`
5. `19 List Pending Overtime For HR`
6. Set `live_overtime_id` to the new pending overtime request id
7. `20 Approve Live Night OT`
8. Run `21 Live Clock In` at or just before your `live_overtime_start`
9. Run `22 Live Clock Out` at or just after your `live_overtime_end`
10. `23 Run Live Payroll`
11. `24 Get Live Payroll Items`
12. Set `live_item_id` to the item id for the live period
13. `25 Get Live Earnings`

What to expect in the live path:

- If the attendance interval really overlaps the approved OT interval inside the `22:00` to `06:00` window, `25` should show:
  - one `isOvertime=true` earnings line for the payable OT hours
  - one separate non-overtime earnings line for night differential on those same OT-backed hours
- Because this live path uses the current attendance endpoint, it may also create ordinary attendance deductions if the employee did not complete their normal scheduled day. That is normal for this API-only check.

## If a token or id does not auto-fill

The login and account requests include simple Bruno response scripts. If your Bruno setup does not persist them, just copy values manually into the active environment:

- `hr_access_token`
- `employee6_access_token`
- `employee10_access_token`
- `employee6_id`
- `employee10_id`
- `employee6_item_id`
- `employee10_item_id`
- `live_overtime_id`
- `live_item_id`
