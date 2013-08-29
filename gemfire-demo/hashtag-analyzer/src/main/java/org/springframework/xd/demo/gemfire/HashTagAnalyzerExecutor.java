/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.springframework.xd.demo.gemfire;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.xd.demo.gemfire.function.HashTagAnalyzer;

/**
 * @author David Turanski
 *
 */
@Component
public class HashTagAnalyzerExecutor implements InitializingBean {
	@Autowired
	HashTagAnalyzer hashTagAnalyzer;
	public Map<String,Integer> run(String targetHashTag) {
		List<Map<String,Integer>> results = hashTagAnalyzer.aggregateAssociatedHashTags(targetHashTag);
		Map<String,Integer> associatedHashTagCounts = results.get(0);
		System.out.println(associatedHashTagCounts.size());
		for (Entry<String,Integer> entry: associatedHashTagCounts.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		return associatedHashTagCounts;
	}
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(hashTagAnalyzer,"hashTagAnalyzer cannot be null");
		
	}
}