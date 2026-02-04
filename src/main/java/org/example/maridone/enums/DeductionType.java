package org.example.maridone.enums;

/**
 * Defines the types of deductions applicable to MaridOne employee payroll.
 * Includes mandatory government contributions, tax brackets, and voluntary deductions.
 */
public enum DeductionType {
    /** Home Development Mutual Fund (HDMF) - Fixed at 2% for employees (₱10k cap). */
    PAGIBIG,

    /** Social Security System - 2026 rate is 5% for employees up to ₱35k MSC. */
    SSS,

    /** Philippine Health Insurance - 2026 rate is 5% (split 50/50 between ER and EE). */
    PHILHEALTH,

    /** Income Tax: 0% for annual income below ₱250,000. */
    BRACKET_LEVEL_ONE,

    /** Income Tax: 15% of excess over ₱250,000. */
    BRACKET_LEVEL_TWO,

    /** Income Tax: ₱22,500 + 20% of excess over ₱400,000. */
    BRACKET_LEVEL_THREE,

    /** Income Tax: ₱102,500 + 25% of excess over ₱800,000. */
    BRACKET_LEVEL_FOUR,

    // --- Added Missing Deduction Types ---

    /** Repayment for SSS Salary or Calamity Loans. */
    SSS_LOAN,

    /** Repayment for Pag-IBIG Multi-Purpose or Housing Loans. */
    PAGIBIG_LOAN,

    /** Private Health Insurance/HMO premiums beyond PhilHealth coverage. */
    HMO_PREMIUM,

    /** Any other company-specific or miscellaneous deductions. */
    OTHER
}
