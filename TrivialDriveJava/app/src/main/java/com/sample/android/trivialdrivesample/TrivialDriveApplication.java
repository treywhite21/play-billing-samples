/*
 * Copyright (C) 2021 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trwhite.java.trivialdrive;

import android.app.Application;

import com.trwhite.java.trivialdrive.billing.BillingDataSource;
import com.trwhite.java.trivialdrive.db.GameStateModel;

public class TrivialDriveApplication extends Application {
    public AppContainer appContainer;

    // Container of objects shared across the whole app
    public class AppContainer {
        final GameStateModel gameStateModel = new GameStateModel(TrivialDriveApplication.this);
        final BillingDataSource billingDataSource = BillingDataSource.getInstance(
                TrivialDriveApplication.this,
                TrivialDriveRepository.INAPP_SKUS,
                TrivialDriveRepository.SUBSCRIPTION_SKUS,
                TrivialDriveRepository.AUTO_CONSUME_SKUS);
        final public TrivialDriveRepository trivialDriveRepository = new TrivialDriveRepository(
                billingDataSource,
                gameStateModel);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer();
    }
}
