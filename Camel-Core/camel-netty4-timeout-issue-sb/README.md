# netty4-timeout-issue
It seems that camel netty4 does not enforce the read timeout for every single request. It appears to be channel specific. 
So, if the response for a channel is received, the timeout handler is removed for it and if a subsequent request uses the same channel, the read timeout is never triggered for it.
