# Fun with Java

Aimed at discovering the language while I teach others.

Start with `git checkout e1`.

There are 2 exercises now.

## Concurrency package

1. Simple thread exercise - shows basic threads API at work (sleep, interrupt, join, start)
2. Parking<String> - good to demonstrate run vs start, (un)synchronized methods and data races and the difficuly in showing short-lived threads on monitoring tools (especially if they're not named)
3. NamedThreadFactory - codifies good practice in naming threads while creating them.

### Exercise 2

Write a simple thread factory to create a thread easily.

1. Creates a thread
2. Names it
3. Returns a named thread that one can run or start.

Replace code in Parking class with this new NamedThreadFactory class.

## Done? 

Follow up with `git checkout e3`
