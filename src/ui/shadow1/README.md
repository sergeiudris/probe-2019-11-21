An example shadow-cljs project (a modification of [conduit]) with:
 - devcards 
 - shadow-cljs 
 - re-frame
 - re-frame-10x
 - re-frame-trace
 - split/aliased dependencies for dev and prod


## Development server
```
$ yarn dev
```
open http://localhost:3000 in your browser

## Release
```
$ yarn release
```

## Devcards
```
$ yarn devcards
```
open http://localhost:4001/devcards.html in your browser

## Testing
```
$ yarn compile-tests
$ CHROME_BIN=<path to chorme or chormium> yarn test
```

[conduit]: https://github.com/jacekschae/conduit
