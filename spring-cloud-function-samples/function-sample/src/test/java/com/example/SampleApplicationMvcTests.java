/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @author Dave Syer
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore
public class SampleApplicationMvcTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void words() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/words")).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(content().string("[\"foo\",\"bar\"]"));
	}

	@Test
	public void uppercase() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/uppercase").contentType(MediaType.TEXT_PLAIN).content("foo")).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(content().string("FOO"));
	}

	@Test
	public void lowercase() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/lowercase").contentType(MediaType.TEXT_PLAIN).content("FOO")).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(content().string("[\"foo\"]"));
	}

	@Test
	public void lowercaseMulti() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/lowercase").contentType(MediaType.APPLICATION_JSON).content("[\"FOO\"]")).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(content().string("[\"foo\"]"));
	}

}
