/*
 *******************************************************************************
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.eclipse.microprofile.fault.tolerance.tck.config.clientserver;

import java.sql.Connection;

import org.eclipse.microprofile.faulttolerance.Retry;

import jakarta.enterprise.context.RequestScoped;

/**
 * A client to support Fault Tolerance Configuration tests.
 * 
 * @author <a href="mailto:neilyoung@uk.ibm.com">Neil Young</a>
 *
 */
@RequestScoped
@Retry(maxRetries = 5)
public class ConfigClassLevelClient {

    private int counterForInvokingConnenectionService = 0;

    public Connection serviceA() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connectionService();
    }

    @Retry(maxRetries = 1)
    public Connection serviceB() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connectionService();
    }

    private Connection connectionService() {
        counterForInvokingConnenectionService++;
        throw new RuntimeException("Connection failed");
    }

    public int getCounterForInvokingConnectionService() {
        return counterForInvokingConnenectionService;
    }
}
