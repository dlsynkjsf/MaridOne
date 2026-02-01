package org.example.maridone.core.spec;

import org.example.maridone.core.bank.BankAccount;
import org.springframework.data.jpa.domain.Specification;

public class BankSpecs {

    public static Specification<BankAccount> isActive(Boolean isActive) {
        return (root, query, cb) -> cb.equal(root.get("is_active"), isActive);
    }

    public static Specification<BankAccount> hasEmployeeId (Long employeeId) {
        return (root, query, cb) -> {
            if (employeeId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("emp_id"), employeeId);
        };

    }
}
