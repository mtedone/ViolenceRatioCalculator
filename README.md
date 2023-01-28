# ViolenceRatioCalculator
This is some fun test project to calculate the likelihood of violence based on the following equation: 
```
V = (PPL * AE) / (AR * WW)
Where: 
V = Calculated Violence Ratio
PPL = Number Of People
AE = Average Expectation 
AR = Available Resources
WW = Willingness to work honestly and hard to achieve expectations
```

The unit tests define a set of scenarios that suggests this equation, while not perfect, make sense: 

- Optimal values lead to the lowest violence ratio
- Lower Available Resources leads to higher violence
- Higher Expectations leads to higher violence
- Lower Willingness leads to higher violence
- When all other variables are equal, more people lead to more violence
- No resources should lead to the highest violence ratio

By optimal values, here it's meant values that lead to the lowest possible ratio. E.g. more resources, lower expectations and higher willingness to work honestly for the desired expectations are all optimal values. 
