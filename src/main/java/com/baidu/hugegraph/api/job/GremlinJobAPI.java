/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.api.job;

import java.util.Map;

import com.baidu.hugegraph.api.gremlin.GremlinRequest;
import com.baidu.hugegraph.api.task.TaskAPI;
import com.baidu.hugegraph.client.RestClient;
import com.baidu.hugegraph.rest.RestResult;
import com.baidu.hugegraph.structure.constant.HugeType;

public class GremlinJobAPI extends JobAPI {

    private static final String JOB_TYPE = "gremlin";

    public GremlinJobAPI(RestClient client, String graph) {
        super(client, graph);
    }

    @Override
    protected String jobType() {
        return JOB_TYPE;
    }

    public long execute(GremlinRequest request) {
        RestResult result = this.client.post(this.path(), request);
        @SuppressWarnings("unchecked")
        Map<String, Object> task = result.readObject(Map.class);
        return TaskAPI.parseTaskId(task);
    }
}
