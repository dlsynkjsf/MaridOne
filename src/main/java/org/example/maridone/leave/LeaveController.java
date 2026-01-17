package org.example.maridone.leave;

import jakarta.validation.Valid;
import org.example.maridone.enums.LeaveType;
import org.example.maridone.leave.balance.BalanceRequestDto;
import org.example.maridone.leave.balance.LeaveBalance;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/employee/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    //get balance
//    @GetMapping("/balance/{empId}")
//    @PreAuthorize("@userCheck.isSelf(#empId, authentication.getName())")
//    public LeaveBalance getBalance(@PathVariable Long empId){
//
//    }

    //create balance
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
    //patch balance (updates if a request has been made)


    //create leave request
    //update leave request that only hr can use to update the request
}
