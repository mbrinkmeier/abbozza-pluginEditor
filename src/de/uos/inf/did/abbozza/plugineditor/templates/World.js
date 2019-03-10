/**
 * In this file the world's view and its operations are defined.
 */


/**
 * World is a static object, wrapping the actual world.
 * The constructor's parameter is the id of the world.
 *
 * Several operations have to be defined or overridden.
 */
var World = new AbbozzaWorld("console_example");

/**
 * This operation initializes the view of the world.
 * Its only parameter is the HTML DIV Element in which the view has to be
 * inserted.
 *
 * @param {type} div The DIV Element into which the view has to be inserted.
 * @returns {undefined}
 */
World.initView = function(div) {
    // Furtherdown, the handler for the view is defined.
    this.mycon = new Console(div);
}


/**
 * The resetWorld() operation is called, each time the program or wolrd has to
 * reset to a dedfined state.
 */
World.resetWorld = function() {
    this.mycon.clear();
};


/**
 * Here we define the model for the view.
 */

Console.NO_INPUT = 0;
Console.READLINE = 1;
Console.READKEY = 2;

function Console(view) {

    this.minCursorPos_ = 0;
    this.inputMode_ = Console.READLINE;

    this.parent_ = document.createElement("div");
    this.parent_.className = "consoleParent";
    view.appendChild(this.parent_);
    this._activateKeyboard(this.parent_);

    // Create console_
    this.console_ = document.createElement("textarea");
    this.console_.Console = this;
    this.console_.disabled = true;
    this.parent_.appendChild(this.console_);

    // Set size of console
    this.console_.className = "console";

    // Add event handlers
    this.console_.addEventListener("keydown", this.keydown);

    this.fontSizeDiv = document.getElementById("fontsize");
    this.fontSizeDiv.value = 14;
    this.console_.style.fontSize="14pt";

    this.fontSizeDiv.oninput = function(event) {
        World.mycon.console_.style.fontSize = this.value + "pt";
    }
};


Console.prototype.clear = function() {
    this.console_.value = "";
    this.minCursorPos_ = 0;
}

Console.prototype.print = function (text) {
    this.console_.value = this.console_.value + text;
    this.minCursorPos_ = this.console_.value.length + 1;
    this.console_.scrollTop = this.console_.scrollHeight;
    this.console_.selectionStart = this.console_.value.length + 1;
    this.console_.selectionEnd = this.console_.selectionStart;
};


Console.prototype.println = function (text) {
    // this.console_.value = this.console_.value + text + "\n";
    this.insertCharAt(text + "\n", this.minCursorPos_);
    this.minCursorPos_ = this.console_.value.length + 1;
    this.console_.scrollTop = this.console_.scrollHeight;
    this.console_.selectionStart = this.console_.value.length + 1;
    this.console_.selectionEnd = this.console_.selectionStart;
};


Console.prototype.readkey = function (callback = null) {
    this.inputMode_ = Console.READKEY;
    this.minCursorPos_ = this.console_.value.length + 1;
    this.console_.selectionStart = this.console_.value.length;
    this.console_.selectionEnd = this.console_.selectionStart;
    this.callback_ = callback;
    this.console_.disabled = false;
    this.console_.focus();
};

Console.prototype.readline = function (callback = null) {
    this.inputMode_ = Console.READLINE;
    this.minCursorPos_ = this.console_.value.length + 1;
    this.console_.selectionStart = this.console_.value.length;
    this.console_.selectionEnd = this.console_.selectionStart;
    this.callback_ = callback;
    this.console_.scrollTop = this.console_.scrollHeight;
    this.console_.disabled = false;
    this.console_.focus();
};


Console.prototype.addLineListener = function(callback) {
    this.console_.addEventListener("lineread",callback);
}


Console.prototype.insertCharAt = function (s, pos) {
    if ( pos < this.minCursorPos_ ) {
        pos = this.console_.value.length;
    }

    var prefix = this.console_.value.substring(0, pos);
    var suffix = this.console_.value.substring(pos);

    this.console_.value = prefix + s + suffix;
    this.console_.selectionStart = pos + 1;
    this.console_.selectionEnd = this.console_.selectionStart;
    this.console_.scrollTop = this.console_.scrollHeight;
};

Console.prototype.deleteCharAt = function (pos) {
    var prefix = this.console_.value.substring(0, pos - 1);
    var suffix = this.console_.value.substring(pos);

    this.console_.value = prefix + suffix;
    this.console_.selectionStart = pos-1;
    this.console_.selectionEnd = this.console_.selectionStart;
    this.console_.scrollTop = this.console_.scrollHeight;
};



/**
 * This event handler is executed by console_
 */
Console.prototype.keydown = function (event) {
    var con = this.Console;

    if (con.inputMode_ == Console.NO_INPUT) {
        event.preventDefault();
        return;
    }

    if (con.inputMode_ == Console.READKEY) {
        event.preventDefault();

        if (con.callback_ != null) {
            con.callback_.call(this, event.key);
            con.console_.disabled = true;
        }

        var myEvent = document.createEvent('Event');
        myEvent.initEvent('keyread', true, true);
        myEvent.key = event.key;
        con.inputMode_ = Console.READLINE;
        this.dispatchEvent(myEvent);
        return;
    }

    switch (event.key) {
        case "Backspace" :
            if (this.selectionStart >= con.minCursorPos_) {
                con.deleteCharAt(this.selectionStart);
            }
            break;
        case "Delete" :
            con.deleteCharAt(this.selectionStart + 1);
            break;
        case "ArrowLeft" :
            if (this.selectionStart < con.minCursorPos_) {
                this.selectionStart = con.minCursorPos_ - 1;
            } else {
                this.selectionStart--;
            }
            this.selectionEnd = this.selectionStart;
            break;
        case "ArrowRight" :
            if (this.selectionStart >= this.value.length) {
                this.selectionStart = this.value.length;
            } else {
                this.selectionStart++;
            }
            this.selectionEnd = this.selectionStart;
            break;
        case "Enter":
            if (con.inputMode_ == Console.READLINE) {
                var line = this.value.substring(con.minCursorPos_ - 1);

                if (con.callback_ != null) {
                    con.callback_.call(this, line);
                }
                var myEvent = document.createEvent('Event');
                myEvent.initEvent('lineread', true, true);
                myEvent.line = line;
                con.inputMode_ = Console.READLINE;
                this.dispatchEvent(myEvent);
                con.minCursorPos_ = this.value.length + 1;
            }
            con.insertCharAt("\n", this.selectionStart);
            break;
        default:
            if (event.key.length == 1) {
                con.insertCharAt(event.key, this.selectionStart);
            }
    }
    event.preventDefault();
};



/**
 * The initialization for the source interpreter
 *
 * @param {type} interpreter
 * @param {type} scope
 * @returns {undefined}
 */
World.initSourceInterpreter = function(interpreter,scope) {
    AbbozzaInterpreter.createWrappers( interpreter, scope,
        [
            [ "println" ,false,World.mycon,World.mycon.println ],
            [ "print"   ,false,World.mycon,World.mycon.print ],
            [ "readline",true,World.mycon,World.mycon.readline ],
            [ "readkey",true,World.mycon,World.mycon.readkey ],
            [ "clear",false,World.mycon,World.mycon.clear ]
        ]
    );
    // interpreter.setProperty(scope,"println",AbbozzaInterpreter.createWrapper(interpreter,false,World.mycon,World.mycon.println));
    // interpreter.setProperty(scope,"readline",AbbozzaInterpreter.createWrapper(interpreter,true,World.mycon,World.mycon.readline));
    // interpreter.setProperty(scope,"print",AbbozzaInterpreter.createWrapper(interpreter,false,World.mycon,World.mycon.print));
    // interpreter.setProperty(scope,"readkey",AbbozzaInterpreter.createWrapper(interpreter,true,World.mycon,World.mycon.readkey));
    // interpreter.setProperty(scope,"clear",AbbozzaInterpreter.createWrapper(interpreter,false,World.mycon,World.mycon.clear));

    // interpreter.setProperty(scope,"println",interpreter.createNativeFunction(World.printlnWrapper));
    // interpreter.setProperty(scope,"readline",interpreter.createAsyncFunction(World.readlineWrapper));
    // interpreter.setProperty(scope,"print",interpreter.createNativeFunction(World.printWrapper));
    // interpreter.setProperty(scope,"readkey",interpreter.createAsyncFunction(World.readkeyWrapper));
    // interpreter.setProperty(scope,"clear",interpreter.createNativeFunction(World.clearWrapper));
}

/*
World.printWrapper = function(text) {
    World.mycon.print(text);
}

World.printlnWrapper = function(text) {
    World.mycon.println(text);
}

World.clearWrapper = function() {
    World.mycon.clear();
}

World.readkeyWrapper = function(callback) {
    return World.mycon.readkey( function(text) {
        callback(text);
    });
}

World.readlineWrapper = function(callback) {
    return World.mycon.readline( function(text) {
        callback(text);
    });
}
*/
