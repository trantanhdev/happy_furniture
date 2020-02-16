package com.example.hf.di.account;

import com.example.hf.repositories.AccountRespository;
import com.example.hf.repositories.lds.AccountLocalDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountModule {

  @Provides
  AccountRespository getAccountRepository() {
    return new AccountLocalDataSource();
  }

}
