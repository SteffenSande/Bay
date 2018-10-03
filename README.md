# Bay
Auction application using java EE as a part of the course DAT250 

# Tips and Docs:
- [Relationship between](https://stackoverflow.com/questions/36032978/what-is-the-relationship-between-jersey-jaxb-jax-rs-moxy-jackson-eclipselin) XML, JSON, JAXB, Jackson and Moxy.
- [Customizing JSON representation](http://blog.bdoughan.com/2013/06/moxy-is-new-default-json-binding.html)
- [Persist both parent and childs](https://stackoverflow.com/a/14153341/4080590), like with Product and Bids.

## Troubleshooting
- There might be a problem with the default postgres driver for glassfish. Do the 3 first steps of [this guide](http://www.hildeberto.com/2010/02/creating-a-connection-pool-to-postgresql-on-glassfish-v3.html) to fix.
- "EntityManagerFactory closed" may happen after larger refactorings. Just stopping and starting the domain should fix.

## Ideas
- [This guide](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/) has a very interesting way to build query specifications for DAO. Not needed yet.

## Interesting
- [This problem](https://stackoverflow.com/questions/20116444/severe-a-message-body-writer-for-java-class-java-util-arraylist-and-mime-media) had 7 different solutions, all solutions working for someone!