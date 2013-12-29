/*
 * Copyright (c) 2013, Josef Raška
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
 * This class can ensure that hanging milliseconds on screen will be like only values 1.05, 1.10, not 1,06, 1,11 for the interval of 50 ms
 */
public final class RoundingMillisDecoratorFormatter implements IMillisFormatter
{
	//region Fields

	private final IMillisFormatter mFormatter;
	private final long mIncrement;
	private final long mIncrementRoundingThreshold;

	//endregion

	//region Constructors

	public RoundingMillisDecoratorFormatter(IMillisFormatter formatter, final long increment)
	{
		if (formatter == null)
		{
			throw new IllegalArgumentException("Formatter cannot be null.");
		}

		if (increment < 1)
		{
			throw new IllegalArgumentException("Increment must be positive.");
		}

		mFormatter = formatter;
		mIncrement = increment;
		mIncrementRoundingThreshold = (increment / 2 - 1);
	}

	//endregion

	//region Properties

	public IMillisFormatter getFormatter()
	{
		return mFormatter;
	}

	public long getIncrement()
	{
		return mIncrement;
	}

	//endregion

	//region IMillisFormatter implementation

	@Override
	public String formatElapsedTime(long totalMs)
	{
		long newMillis = countNewMillis(totalMs);

		return mFormatter.formatElapsedTime(newMillis);
	}

	//endregion

	//region Methods

	long countNewMillis(long totalMs)
	{
		//this removes additional milliseconds
		long newMillis = (totalMs / mIncrement) * mIncrement;

		long removingPart = totalMs % mIncrement;

		//rounding to up border
		if (removingPart > mIncrementRoundingThreshold)
		{
			newMillis += mIncrement;
		}

		return newMillis;
	}

	public static IMillisFormatter decorateWithRounding(IMillisFormatter formatter, long increment)
	{
		//do not decorate already decorated formatters
		if (formatter instanceof RoundingMillisDecoratorFormatter && ((RoundingMillisDecoratorFormatter) formatter).getIncrement() == increment)
		{
			return formatter;
		}

		return new RoundingMillisDecoratorFormatter(formatter, increment);
	}

	//endregion
}
