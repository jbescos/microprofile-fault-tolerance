/*
 *******************************************************************************
 * Copyright (c) 2016-2017 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.fault.tolerance.tck.visibility.retry;

import java.io.IOException;
import java.sql.Connection;

import org.eclipse.microprofile.faulttolerance.Retry;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@RS(RetryServiceType.BASE_ROC_RETRY_REDEFINED_ON_METHOD)
@Retry(maxRetries = 5)
/*
 * we define a Retry annotation here to be sure that it is not taken into account during resolution of the annotation
 * retrieval on the method. Tests will ensure that 'maxRetries' for the service method will not be 5 (defined on class)
 * nor 4 defined on parent class
 */
public class RetryOnClassServiceOverrideMethodLevel extends BaseRetryOnClassService {
    @Override
    @Retry(maxRetries = 4)
    public Connection service() throws IOException {
        return super.service();
    }
}
