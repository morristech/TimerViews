/*
 * Copyright (c) 2014, Josef Raška
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

package com.jraska.time.format;

/**
 * Implementations can format raw ms values to human readable String
 */
public interface IMillisFormatter
{
	//region Methods

	/**
	 * Formats raw milliseconds to some readable text.
	 *
	 * @param totalMs Raw ms.
	 * @return Text representation of milliseconds.
	 */
	String formatElapsedTime(long totalMs);

	//endregion
}
