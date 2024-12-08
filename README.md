# xml on client and server

This xml project provides a dom model, which can be used on both client (browser) and server side.

Besides dom model, xpath 1.0 api is also supported on both sides.

The dom model in this project is semi thread-safe.

On client side (browser), it is totally thread-safe due to the JavaScript's single-threaded nature.

On server side, it is thread-safe for read-only operations, which means XPath operations can be parallel executed.

For write operations, the <code>Document</code> object needs to be external synchronized. In other words, no any read
and/or other write operations of different thread are allowed during a writing process.
