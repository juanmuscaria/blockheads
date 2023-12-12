# BHServer
Not actually a server, more of an improved version of [BlockProxy](https://github.com/juanmuscaria/BlockProxy)
All you can find here is a working ENet proxy and packet analyzer with some progress on reverse engineering the network 
protocol and world formats

For proxying the game, checkout the class `com.juanmuscaria.blockheads.intercept.BHInterceptor`,
it has all the fancy stuff for that.

# Warning, disgusting code quality
This mess contains code from when I was stupid, 
with little to no clean up from less stupid but still stupid present me,
It's a complete mess, and the jna code should not be used *at all*, most stuff here is for research purposes