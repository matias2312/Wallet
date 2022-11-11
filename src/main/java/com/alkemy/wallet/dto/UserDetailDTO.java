package com.alkemy.wallet.dto;

import com.alkemy.wallet.model.entity.AccountEntity;
import com.alkemy.wallet.model.entity.FixedTermDepositEntity;
import com.alkemy.wallet.model.entity.RoleEntity;
import com.alkemy.wallet.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class UserDetailDTO {
    private String firstName;
    private String lastName;
    private String email;
    private RoleEntity role;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Set<AccountEntity> account;
    private Set<FixedTermDepositEntity> fixedTermDeposit;

    public UserDetailDTO(UserEntity userEntity) {
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
        this.role = userEntity.getRole();
        this.creationDate = userEntity.getCreationDate();
        this.updateDate = userEntity.getUpdateDate();
        this.account = userEntity.getAccount();
        this.fixedTermDeposit = userEntity.getFixedTermDeposit();
    }
}
