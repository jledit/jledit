**Vial**
---------
An extensible [Jansi](http://jansi.fusesource.org/) & [Jline](https://github.com/jline/jline2 "Jline") based Console Text Editor.

The editor is using [Jansi](http://jansi.fusesource.org/) for ansi escape sequence manipulation. It also uses [Jline](https://github.com/jline/jline2 "Jline") terminals, streams and key maps.

**Editor Features**

* Scrolling
* Undo / Redo functionality
* Forward & Backward searching and highlighting
* Color themes

**Building**

Buidling requires:
* Java 1.6+
* Maven 3

    >mvn clean install

**Running**

Once the build is done:

    >cd vial/target
    >tar -zxvf vial-1.0-SNAPSHOT-bin.tar.gz
    > cd vial-1.0-SNAPSHOT
    > ./bin/vial /path/to/myfile

or on Windows:

    >cd vial/target
    >tar -zxvf vial-1.0-SNAPSHOT-bin.zip
    > cd vial-1.0-SNAPSHOT/bin
    > vial.bat /path/to/myfile



**Knows Issues & Limitations**

* The whole file is loaded in memory.
* Resizing of terminal is not well supported.
* Addiitional work on key mappings per Operating System is required.
* Save will convert all **\r** characters to **\n**.
* The base ConsoleEditor implementation needs to be simplified.
* Need a cleaner way to map **backspace** and **delete** chars on osx.


