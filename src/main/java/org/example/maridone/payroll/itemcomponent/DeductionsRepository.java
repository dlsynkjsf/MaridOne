package org.example.maridone.payroll.itemcomponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeductionsRepository extends JpaRepository<DeductionsLine, Long> {

    List<DeductionsLine> findByPayrollItem_ItemId(Long itemId);
}
