package org.example.maridone.overtime.spec;

import jakarta.persistence.criteria.JoinType;
import org.example.maridone.enums.Status;
import org.example.maridone.overtime.OvertimeRequest;
import org.springframework.data.jpa.domain.Specification;

public class OvertimeSpecs {
    public static Specification<OvertimeRequest> hasEmployeeId(Long empId) {
        return (root, query, cb) -> {
            if (empId == null) {
                return cb.conjunction();
            }
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("employee", JoinType.LEFT);
            }
            return cb.equal(root.get("employee").get("employeeId"), empId);
        };
    }

    public static Specification<OvertimeRequest> hasStatus(Status status) {
        return (root, query, cb) -> cb.equal(root.get("requestStatus"), status);
    }

    public static Specification<OvertimeRequest> hasOvertimeId(Long overtimeId) {
        return (root, query, cb) -> cb.equal(root.get("overtimeId"), overtimeId);
    }
}
