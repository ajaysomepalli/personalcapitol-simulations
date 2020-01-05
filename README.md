# personalcapitol-simulations
It generates 10,000 simulations for the investment's future value with given risk, return and inflation for given number of years.

Information Provided:
  1. Initial investment of $100,000 and returns, risk for an investment is provided.
  2. Calculate Median, 10% Best case and 10% Worst case for the term of 20 years with an inflation of 3.5% for both aggresive and very conservative approach.

Steps Involved:
  1. Calculate 10,000 simulations using Guassian Distribution with the given Return(Mean) and Risk(Standard Deviation) for an               year. Simulation = RandomGuassianNumber * Standard Deviation + Mean [As the random gaussian number is calculated for 0 meand and 1 Standard Deviation].
  2. Calculate the future value of the investment of $100,000 for the 10,000 simulations generated for that year. Future value = I * (1 + R)^T [I - Initila investment, R - Rate of return, here it is simulation calculated and T is Time period in years].
  3. Repeat the above 2 steps for 20 years.
  4. Calculate the Median, Best and Worst case for the 20th year.
  5. Run the same process for both aggressive and very conservative approach.
  
Test Cases:
  1. If there is no risk and returns = inflation, the future value should be the same as initial investment. In this case returns = 0, risk = 0, inflation = 0 and initial investment is $100,000 the median value is $100,000.
  2. If inflation is greater than the returns, the future value is less than the initial investment. In this case, returns = 0, risk = 0, inflation = 3% and the initial investment is $100,000 the best case is < $100,000.
  3. If there is a fixed return with no risk and no inflation, the future value should be equal tot eh calculated value. In this case return = 5%, risk = 0 and inflation is 0 and initial investment is $100,000 the median value is $265,329.77.
