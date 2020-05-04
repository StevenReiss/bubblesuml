/* CustomDrawingParser.java */
/* Generated By:JavaCC: Do not edit this line. CustomDrawingParser.java */
package com.baselet.element.facet.customdrawings.gen;


import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.LineType;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.diagram.draw.helper.ColorOwn;
import com.baselet.element.facet.customdrawings.CustomDrawingParserRuntimeException;

public abstract class CustomDrawingParser implements CustomDrawingParserConstants {

 // JavaCC doesn't support user enriched constructors (adding additional parameters)
 // therefore we use abstract getters
 public abstract double getWidth();
 public abstract double getHeight();
 public abstract DrawHandler getDrawHandler();

 /***
	 * Is used to implement nested tasks.
	 * This is used to support the different setting commands after the drawing command.
	 * These need to store the old value and afterwards reset it to this value.
	 */
 private abstract class RecRunnable {
  private RecRunnable inner;

  public RecRunnable(RecRunnable i){
   this.inner = i;
  }

  public abstract void run();

  protected void runInner() {
   if(inner != null)
    inner.run();
  }
 }

/**
 * The main function which parses a line.
 * Whitespaces and comments are skipped (see Tokens)
 */
  final public void start() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 23:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 23:{
        drawLine();
        break;
        }
      case 26:{
        drawRectangle();
        break;
        }
      case 27:{
        drawRectangleRound();
        break;
        }
      case 28:{
        drawCircle();
        break;
        }
      case 29:{
        drawEllipse();
        break;
        }
      case 30:{
        drawArc();
        break;
        }
      case 31:{
        drawText();
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(0);
  }

  final public void drawLine() throws ParseException {final double x1, y1, x2, y2;
 RecRunnable callStack = null;
    jj_consume_token(23);
    x1 = doubleTerm();
    jj_consume_token(24);
    y1 = doubleTerm();
    jj_consume_token(24);
    x2 = doubleTerm();
    jj_consume_token(24);
    y2 = doubleTerm();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run(){
   //System.out.println(String.format("drawLine(%f, %f, %f, %f);", x1,y1,x2,y2));
   getDrawHandler().drawLine(x1, y1, x2, y2);
  }};
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:
      case LT:
      case LW:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        callStack = fg(callStack);
        break;
        }
      case LT:{
        callStack = lt(callStack);
        break;
        }
      case LW:{
        callStack = lw(callStack);
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
callStack.run();
  }

  final public void drawRectangle() throws ParseException {final double x, y, width, height;
 RecRunnable callStack = null;
    jj_consume_token(26);
    x = doubleTerm();
    jj_consume_token(24);
    y = doubleTerm();
    jj_consume_token(24);
    width = doubleTerm();
    jj_consume_token(24);
    height = doubleTerm();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run() {
   //System.out.println(String.format("drawRectangle(%f, %f, %f, %f);", x,y,width,height));
   getDrawHandler().drawRectangle(x, y, width, height);
  }};
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:
      case BG:
      case LT:
      case LW:
      case TRANSPARENCY:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        callStack = fg(callStack);
        break;
        }
      case BG:{
        callStack = bg(callStack);
        break;
        }
      case LT:{
        callStack = lt(callStack);
        break;
        }
      case LW:{
        callStack = lw(callStack);
        break;
        }
      case TRANSPARENCY:{
        callStack = transparency(callStack);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
callStack.run();
  }

  final public void drawRectangleRound() throws ParseException {final double x, y, width, height, radius;
 RecRunnable callStack = null;
    jj_consume_token(27);
    x = doubleTerm();
    jj_consume_token(24);
    y = doubleTerm();
    jj_consume_token(24);
    width = doubleTerm();
    jj_consume_token(24);
    height = doubleTerm();
    jj_consume_token(24);
    radius = doubleTerm();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run() {
   //System.out.println(String.format("drawRectangleRound(%f, %f, %f, %f, %f);", x,y,width,height,radius));
   getDrawHandler().drawRectangleRound(x, y, width, height, radius);
  }};
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:
      case BG:
      case LT:
      case LW:
      case TRANSPARENCY:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        callStack = fg(callStack);
        break;
        }
      case BG:{
        callStack = bg(callStack);
        break;
        }
      case LT:{
        callStack = lt(callStack);
        break;
        }
      case LW:{
        callStack = lw(callStack);
        break;
        }
      case TRANSPARENCY:{
        callStack = transparency(callStack);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
callStack.run();
  }

  final public void drawCircle() throws ParseException {final double x, y, radius;
 RecRunnable callStack = null;
    jj_consume_token(28);
    x = doubleTerm();
    jj_consume_token(24);
    y = doubleTerm();
    jj_consume_token(24);
    radius = doubleTerm();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run() {
   getDrawHandler().drawCircle(x, y, radius);
  }};
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:
      case BG:
      case LT:
      case LW:
      case TRANSPARENCY:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        callStack = fg(callStack);
        break;
        }
      case BG:{
        callStack = bg(callStack);
        break;
        }
      case LT:{
        callStack = lt(callStack);
        break;
        }
      case LW:{
        callStack = lw(callStack);
        break;
        }
      case TRANSPARENCY:{
        callStack = transparency(callStack);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
callStack.run();
  }

  final public void drawEllipse() throws ParseException {final double x, y, width, height;
 RecRunnable callStack = null;
    jj_consume_token(29);
    x = doubleTerm();
    jj_consume_token(24);
    y = doubleTerm();
    jj_consume_token(24);
    width = doubleTerm();
    jj_consume_token(24);
    height = doubleTerm();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run() {
   getDrawHandler().drawEllipse(x, y, width, height);
  }};
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:
      case BG:
      case LT:
      case LW:
      case TRANSPARENCY:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        callStack = fg(callStack);
        break;
        }
      case BG:{
        callStack = bg(callStack);
        break;
        }
      case LT:{
        callStack = lt(callStack);
        break;
        }
      case LW:{
        callStack = lw(callStack);
        break;
        }
      case TRANSPARENCY:{
        callStack = transparency(callStack);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
callStack.run();
  }

  final public void drawArc() throws ParseException {final double x, y, width, height, start, extent;
 final boolean open;
 RecRunnable callStack = null;
    jj_consume_token(30);
    x = doubleTerm();
    jj_consume_token(24);
    y = doubleTerm();
    jj_consume_token(24);
    width = doubleTerm();
    jj_consume_token(24);
    height = doubleTerm();
    jj_consume_token(24);
    start = doubleTerm();
    jj_consume_token(24);
    extent = doubleTerm();
    jj_consume_token(24);
    open = booleanConstant();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run() {
   getDrawHandler().drawArc(x, y, width, height, start, extent, open);
  }};
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:
      case BG:
      case LT:
      case LW:
      case TRANSPARENCY:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        callStack = fg(callStack);
        break;
        }
      case BG:{
        callStack = bg(callStack);
        break;
        }
      case LT:{
        callStack = lt(callStack);
        break;
        }
      case LW:{
        callStack = lw(callStack);
        break;
        }
      case TRANSPARENCY:{
        callStack = transparency(callStack);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
callStack.run();
  }

  final public void drawText() throws ParseException {final String text;
 final double x, y;
 final AlignHorizontal alignment;
 RecRunnable callStack = null;
    jj_consume_token(31);
    text = simpleString();
    jj_consume_token(24);
    x = doubleTerm();
    jj_consume_token(24);
    y = doubleTerm();
    jj_consume_token(24);
    alignment = horizontalAlignment();
    jj_consume_token(25);
callStack = new RecRunnable(null) { public void run() {
   //System.out.println(String.format("drawRectangle(%f, %f, %f, %f);", x,y,width,height));
   getDrawHandler().print(text, x, y, alignment);
  }};
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FG:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      callStack = fg(callStack);
    }
callStack.run();
  }

/**
 * sets the foreground color
 */
  final public RecRunnable fg(RecRunnable inner) throws ParseException {final String newColor;
    jj_consume_token(FG);
    newColor = colorOwn();
return new RecRunnable(inner) {
  public void run(){
   ColorOwn oldColor = getDrawHandler().getForegroundColor();
   getDrawHandler().setForegroundColor(newColor);
   runInner();
   getDrawHandler().setForegroundColor(oldColor);
  }
 };
  }

/**
 * sets the background color
 */
  final public RecRunnable bg(RecRunnable inner) throws ParseException {final String newColor;
    jj_consume_token(BG);
    newColor = colorOwn();
return new RecRunnable(inner) {
  public void run(){
   ColorOwn oldColor = getDrawHandler().getBackgroundColor();
   getDrawHandler().setBackgroundColorAndKeepTransparency(newColor);
   runInner();
   getDrawHandler().setBackgroundColor(oldColor);
  }
 };
  }

/**
 * represents a color name (e.g. "red")
 * or a hexadecimal color string 
 * starting with '#' and 
 * containing exactly 6 hexadecimal digits
 * (eg."#CAFFEE")
 */
  final public String colorOwn() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COLOR_OWN_NAME:{
      jj_consume_token(COLOR_OWN_NAME);
      break;
      }
    case COLOR_OWN_HEX:{
      jj_consume_token(COLOR_OWN_HEX);
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return token.image;
  }

/**
 * sets the line type
 */
  final public RecRunnable lt(RecRunnable inner) throws ParseException {final LineType newLineType;
    jj_consume_token(LT);
    newLineType = lineType();
return new RecRunnable(inner) {
  public void run() {
   LineType oldLineType = getDrawHandler().getLineType();
   getDrawHandler().setLineType(newLineType);
   runInner();
   getDrawHandler().setLineType(oldLineType);
  }
 };
  }

  final public LineType lineType() throws ParseException {LineType value;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 32:{
      jj_consume_token(32);
value = LineType.SOLID;
      break;
      }
    case 33:{
      jj_consume_token(33);
value = LineType.DASHED;
      break;
      }
    case 34:{
      jj_consume_token(34);
value = LineType.DOTTED;
      break;
      }
    case 35:{
      jj_consume_token(35);
value = LineType.DOUBLE;
      break;
      }
    case 36:{
      jj_consume_token(36);
value = LineType.DOUBLE_DASHED;
      break;
      }
    case 37:{
      jj_consume_token(37);
value = LineType.DOUBLE_DOTTED;
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return value;
  }

/**
 * sets the line width
 */
  final public RecRunnable lw(RecRunnable inner) throws ParseException {final double newLineWidth;
    jj_consume_token(LW);
    newLineWidth = unsignedDoubleConstant();
return new RecRunnable(inner) {
  public void run() {
   double oldLineWidth = getDrawHandler().getLineWidth();
   getDrawHandler().setLineWidth(newLineWidth);
   runInner();
   getDrawHandler().setLineWidth(oldLineWidth);
  }
 };
  }

/**
 * sets the transparency (background only)
 */
  final public RecRunnable transparency(RecRunnable inner) throws ParseException {final double transparencyVal;
    jj_consume_token(TRANSPARENCY);
    transparencyVal = unsignedDoubleConstant();
if(transparencyVal < 0 || transparencyVal > 100) {
   {if (true) throw new CustomDrawingParserRuntimeException("The transparency value must be between 0 and 100");}
  }
  return new RecRunnable(inner) {
  public void run(){
   ColorOwn oldColor = getDrawHandler().getBackgroundColor();
   double colorTransparencyValue = 255 - transparencyVal * 2.55; /* ColorOwn has 0 for full transparency and 255 for no transparency */
   ColorOwn bgColor = getDrawHandler().getBackgroundColor();
   getDrawHandler().setBackgroundColor(bgColor.transparency((int) colorTransparencyValue));
   runInner();
   getDrawHandler().setBackgroundColor(oldColor);
  }
 };
  }

  final public String simpleString() throws ParseException {
    jj_consume_token(SIMPLE_STRING);
return token.image.substring(1,token.image.length()-1).replace("\\\"", "\"").replace("\\\\", "\\");
  }

  final public boolean booleanConstant() throws ParseException {boolean value;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case FALSE:{
      jj_consume_token(FALSE);
value = false;
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
value = true;
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return value;
  }

  final public AlignHorizontal horizontalAlignment() throws ParseException {AlignHorizontal alignment;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LEFT:{
      jj_consume_token(LEFT);
alignment = AlignHorizontal.LEFT;
      break;
      }
    case CENTER:{
      jj_consume_token(CENTER);
alignment = AlignHorizontal.CENTER;
      break;
      }
    case RIGHT:{
      jj_consume_token(RIGHT);
alignment = AlignHorizontal.RIGHT;
      break;
      }
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return alignment;
  }

// Start of the arithmetical interpreter
  final public 

double doubleTerm() throws ParseException {double v1, v2;
    v1 = doubleProduct();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 32:
      case 38:{
        ;
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 38:{
        jj_consume_token(38);
        v2 = doubleProduct();
v1 = v1 + v2;
        break;
        }
      case 32:{
        jj_consume_token(32);
        v2 = doubleProduct();
v1 = v1 - v2;
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
return v1;
  }

  final public double doubleProduct() throws ParseException {double v1, v2;
    v1 = doubleValue();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 39:
      case 40:{
        ;
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 39:{
        jj_consume_token(39);
        v2 = doubleValue();
v1 = v1 * v2;
        break;
        }
      case 40:{
        jj_consume_token(40);
        v2 = doubleValue();
v1 = v1 / v2;
        break;
        }
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
return v1;
  }

  final public double doubleValue() throws ParseException {double value;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case WIDTH:{
      jj_consume_token(WIDTH);
value = getWidth();
      break;
      }
    case HEIGHT:{
      jj_consume_token(HEIGHT);
value = getHeight();
      break;
      }
    case 32:{
      jj_consume_token(32);
      value = unsignedDoubleConstant();
value = - value;
      break;
      }
    case UNSIGNED_DOUBLE_CONSTANT:{
      value = unsignedDoubleConstant();
      break;
      }
    case 41:{
      jj_consume_token(41);
      value = doubleTerm();
      jj_consume_token(25);
      break;
      }
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return value;
  }

  final public double unsignedDoubleConstant() throws ParseException {
    jj_consume_token(UNSIGNED_DOUBLE_CONSTANT);
double value;
  try {
   value = Double.parseDouble(token.image);
  } catch(NumberFormatException e) {
   // log fatal error, only valid double values are accepted from the parser
   // a double can't be to big, but may loose some precision
   // log.error("Fatal Error: The string '" + token.image + "' couldn't be parsed as double, but the grammar should ensure that the string is parsable.",e);
   {if (true) throw (ParseException) new ParseException("Fatal Error: The string '" + token.image + "' couldn't be parsed as double, but the grammar should ensure that the string is parsable.").initCause(e);}
  }
  return value;
  }

  /** Generated Token Manager. */
  public CustomDrawingParserTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[24];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xfc800000,0xfc800000,0xd000,0xd000,0x1f000,0x1f000,0x1f000,0x1f000,0x1f000,0x1f000,0x1f000,0x1f000,0x1f000,0x1f000,0x1000,0x180000,0x0,0x180,0xe00,0x0,0x0,0x0,0x0,0x20060,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3f,0x0,0x0,0x41,0x41,0x180,0x180,0x201,};
   }

  /** Constructor. */
  public CustomDrawingParser(Provider stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new CustomDrawingParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CustomDrawingParser(String dsl) throws ParseException, TokenMgrException {
      this(new StringProvider(dsl));
  }

  public void ReInit(String s) {
     ReInit(new StringProvider(s));
  }
  /** Reinitialise. */
  public void ReInit(Provider stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new JavaCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new CustomDrawingParserTokenManager(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CustomDrawingParser(CustomDrawingParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CustomDrawingParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[42];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 24; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 42; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage, token_source == null ? null : CustomDrawingParserTokenManager.lexStateNames[token_source.curLexState]);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
