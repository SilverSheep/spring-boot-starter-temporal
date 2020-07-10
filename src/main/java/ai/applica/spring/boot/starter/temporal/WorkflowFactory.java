/*
 *  Copyright (c) 2020 Applica.ai All Rights Reserved
 *
 *  Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"). You may not
 *  use this file except in compliance with the License. A copy of the License is
 *  located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 *  or in the "license" file accompanying this file. This file is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package ai.applica.spring.boot.starter.temporal;

import ai.applica.spring.boot.starter.temporal.config.TemporalProperties.WorkflowOption;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import java.time.Duration;
import lombok.RequiredArgsConstructor;

/**
 * E - workflow interface I - workflow implementation Two parameters so to autowire both by
 * interface and implementation
 */
@RequiredArgsConstructor
public class WorkflowFactory<E, I> {

  private final WorkflowClient workflowClient;
  private final WorkflowOption option;
  private final String key;
  private final Class<E> clazzInterface;

  public E next() {
    WorkflowOptions options =
        WorkflowOptions.newBuilder()
            .setTaskQueue(key)
            .setWorkflowExecutionTimeout(Duration.ofSeconds(option.getExecutionTimeout()))
            .build();

    return workflowClient.newWorkflowStub(clazzInterface, options);
  }
}
