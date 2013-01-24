**JLEdit**
---------
An extensible [Jansi](http://jansi.fusesource.org/) & [Jline](https://github.com/jline/jline2 "Jline") based Console Text Editor.

The editor is using [Jansi](http://jansi.fusesource.org/) for ansi escape sequence manipulation. It also uses [Jline](https://github.com/jline/jline2 "Jline") terminals, streams and key maps.

It works as a standalone editor, but it can also be use from jline based applications.

**Editor Features**

* Scrolling
* Undo / Redo functionality
* Forward & Backward searching and highlighting
* Pluggable content manager, to be able to edit not just files (e.g. blobs, zookeeper znodes, config admin pids etc).
* Color themes

**Building**

You can download a standalone version of the editor from [Maven Central](http://repo1.maven.org/maven2/org/jledit/jledit/0.1.1/).
You can also build the editor from source.

Buidling requires:
* Java 1.6+
* Maven 3

    > mvn clean install

**Running**

Once the build or download is done:

    > cd jledit/target
    > tar -zxvf jledit-0.1.1-bin.tar.gz
    > cd jledit-0.1.1
    > ./bin/jledit /path/to/myfile

or on Windows:

    > cd jledit/target
    > unzip jledit-0.1.1-bin.zip
    > cd jledit-0.1.1/bin
    > jledit.bat /path/to/myfile



**Knows Issues & Limitations**

* The whole file is loaded in memory.
* Resizing of terminal is not well supported.
* Save will convert all **\r** characters to **\n**.
* The base ConsoleEditor implementation needs to be simplified.
* Possible deadlock when shutting closing the editor.


