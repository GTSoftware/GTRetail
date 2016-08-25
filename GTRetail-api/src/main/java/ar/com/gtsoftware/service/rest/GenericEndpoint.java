/*
 * Copyright 2016 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.service.rest;

import ar.com.gtsoftware.model.dto.ResultadoDTO;
import java.time.LocalDateTime;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Path("/generic")
public interface GenericEndpoint {

    @GET
    @Path("/ping")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResultadoDTO getPing();

    @GET
    @Path("/date")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public LocalDateTime getDate();
}
