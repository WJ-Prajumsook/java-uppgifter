package org.wj.prajumsook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

// THIS CLASS SHOULD NOT BE HERE, BUT FOR VIEW PURPOSE
// SHOULD BE IN "account-app-contract" PACKAGE
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Account {

    private String accountName;
    private String balance;
    private String id;

}
