package com.example.hf.models;

import com.example.hf.repositories.AccountRepository;
import com.example.hf.models.data.Account;

public class AccountModel {

  AccountRepository accountRepository;

  public AccountModel() {
    accountRepository = new AccountRepository();
  }

  public Account getById(int id) {
    return accountRepository.getById(id);
  }
}
