
from('timer:groovy?period=10s')
    .routeId('groovy')
    .setBody()
        .simple('Hello Camel K first simple groovy from ${routeId}')
    .to('log:info?showAll=false')
