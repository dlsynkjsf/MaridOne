package org.example.maridone.leave;

import jakarta.validation.Valid;
import org.example.maridone.enums.LeaveType;
import org.example.maridone.leave.balance.BalanceRequestDto;
import org.example.maridone.leave.balance.BalanceResponseDto;
import org.example.maridone.leave.balance.LeaveBalance;
import org.example.maridone.leave.balance.UpdateBalanceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employee/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/balance/{empId}")
    @PreAuthorize("@userCheck.isSelf(#empId, authentication.getName())")
    public List<BalanceResponseDto> getBalance(@PathVariable Long empId){
        return leaveService.getBalance(empId);
    }

    /*
        create balance
        payload:
            Long empId;
            BigDecimal balanceHours;
            private LeaveType leaveType;
     */
    @PostMapping("/balance/{empId}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveBalance> createLeave(
            @PathVariable Long empId,
            @RequestBody @Valid BalanceRequestDto payload){
        LeaveBalance response = leaveService.createLeave(empId,payload);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
    /*
    update a user's leave balance
    payload:
        balanceHours;
        LeaveType leaveType;
        String type;
    */
    @PatchMapping("/balance/update/{empId}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<Void> updateBalance(
            @PathVariable Long empId,
            @RequestBody @Valid UpdateBalanceDto payload) {
        leaveService.updateBalance(empId, payload);

        return ResponseEntity.noContent().build();
    }


    //create leave request
    //update leave request that only hr can use to update the request
}
