/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing
package event

/**
 * Enumeration of key codes used by key events.
 */
object Key extends Enumeration {
  import java.awt.event.KeyEvent._

  object Location extends Enumeration {
    val Left = Value(java.awt.event.KeyEvent.KEY_LOCATION_LEFT)
    val Right = Value(java.awt.event.KeyEvent.KEY_LOCATION_RIGHT)
    val Numpad = Value(java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD)
    val Standard = Value(java.awt.event.KeyEvent.KEY_LOCATION_STANDARD)
    val Unknown = Value(java.awt.event.KeyEvent.KEY_LOCATION_UNKNOWN)
  }
<<<<<<< HEAD
  
  type Modifiers = Int
  
=======

  type Modifiers = Int

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  object Modifier {
    import java.awt.event.InputEvent._
    val Shift = SHIFT_DOWN_MASK
    val Control = CTRL_DOWN_MASK
    val Alt = ALT_DOWN_MASK
    val AltGraph = ALT_GRAPH_DOWN_MASK
    val Meta = META_DOWN_MASK
    def text(mods: Int) = java.awt.event.KeyEvent.getKeyModifiersText(mods)
  }
<<<<<<< HEAD
  
  //def text(k: Value) = java.awt.event.KeyEvent.getKeyText(k.id)
  
=======

  //def text(k: Value) = java.awt.event.KeyEvent.getKeyText(k.id)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val Shift = Value(VK_SHIFT, getKeyText(VK_SHIFT))
  val Control = Value(VK_CONTROL, getKeyText(VK_CONTROL))
  val Alt = Value(VK_ALT, getKeyText(VK_ALT))
  val AltGraph = Value(VK_ALT_GRAPH, getKeyText(VK_ALT_GRAPH))
  val Meta = Value(VK_META, getKeyText(VK_META))
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val Enter = Value(VK_ENTER, getKeyText(VK_ENTER))
  val BackSpace = Value(VK_BACK_SPACE, getKeyText(VK_BACK_SPACE))
  val Tab = Value(VK_TAB, getKeyText(VK_TAB))
  val Cancel = Value(VK_CANCEL, getKeyText(VK_CANCEL))
  val Clear = Value(VK_CLEAR, getKeyText(VK_CLEAR))
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val Pause = Value(VK_PAUSE, getKeyText(VK_PAUSE))
  val CapsLock = Value(VK_CAPS_LOCK, getKeyText(VK_CAPS_LOCK))
  val Escape = Value(VK_ESCAPE, getKeyText(VK_ESCAPE))
  val Space = Value(VK_SPACE, getKeyText(VK_SPACE))
  val PageUp = Value(VK_PAGE_UP, getKeyText(VK_PAGE_UP))
  val PageDown = Value(VK_PAGE_DOWN, getKeyText(VK_PAGE_DOWN))
  val End = Value(VK_END, getKeyText(VK_END))
  val Home = Value(VK_HOME, getKeyText(VK_HOME))
  val Left = Value(VK_LEFT, getKeyText(VK_LEFT))
  val Up = Value(VK_UP, getKeyText(VK_UP))
  val Right = Value(VK_RIGHT, getKeyText(VK_RIGHT))
  val Down = Value(VK_DOWN, getKeyText(VK_DOWN))
  val Comma = Value(VK_COMMA, getKeyText(VK_COMMA))
  val Minus = Value(VK_MINUS, getKeyText(VK_MINUS))
  val Period = Value(VK_PERIOD, getKeyText(VK_PERIOD))
  val Slash = Value(VK_SLASH, getKeyText(VK_SLASH))
  val Key0 = Value(VK_0, getKeyText(VK_0))
  val Key1 = Value(VK_1, getKeyText(VK_1))
  val Key2 = Value(VK_2, getKeyText(VK_2))
  val Key3 = Value(VK_3, getKeyText(VK_3))
  val Key4 = Value(VK_4, getKeyText(VK_4))
  val Key5 = Value(VK_5, getKeyText(VK_5))
  val Key6 = Value(VK_6, getKeyText(VK_6))
  val Key7 = Value(VK_7, getKeyText(VK_7))
  val Key8 = Value(VK_8, getKeyText(VK_8))
  val Key9 = Value(VK_9, getKeyText(VK_9))
  val Semicolon = Value(VK_SEMICOLON, getKeyText(VK_SEMICOLON))
  val Equals = Value(VK_EQUALS, getKeyText(VK_EQUALS))
  val A = Value(VK_A, getKeyText(VK_A))
  val B = Value(VK_B, getKeyText(VK_B))
  val C = Value(VK_C, getKeyText(VK_C))
  val D = Value(VK_D, getKeyText(VK_D))
  val E = Value(VK_E, getKeyText(VK_E))
  val F = Value(VK_F, getKeyText(VK_F))
  val G = Value(VK_G, getKeyText(VK_G))
  val H = Value(VK_H, getKeyText(VK_H))
  val I = Value(VK_I, getKeyText(VK_I))
  val J = Value(VK_J, getKeyText(VK_J))
  val K = Value(VK_K, getKeyText(VK_K))
  val L = Value(VK_L, getKeyText(VK_L))
  val M = Value(VK_M, getKeyText(VK_M))
  val N = Value(VK_N, getKeyText(VK_N))
  val O = Value(VK_O, getKeyText(VK_O))
  val P = Value(VK_P, getKeyText(VK_P))
  val Q = Value(VK_Q, getKeyText(VK_Q))
  val R = Value(VK_R, getKeyText(VK_R))
  val S = Value(VK_S, getKeyText(VK_S))
  val T = Value(VK_T, getKeyText(VK_T))
  val U = Value(VK_U, getKeyText(VK_U))
  val V = Value(VK_V, getKeyText(VK_V))
  val W = Value(VK_W, getKeyText(VK_W))
  val X = Value(VK_X, getKeyText(VK_X))
  val Y = Value(VK_Y, getKeyText(VK_Y))
  val Z = Value(VK_Z, getKeyText(VK_Z))
  val OpenBracket = Value(VK_OPEN_BRACKET, getKeyText(VK_OPEN_BRACKET))
  val BackSlash = Value(VK_BACK_SLASH, getKeyText(VK_BACK_SLASH))
  val CloseBracket = Value(VK_CLOSE_BRACKET, getKeyText(VK_CLOSE_BRACKET))
  val Numpad0 = Value(VK_NUMPAD0, getKeyText(VK_NUMPAD0))
  val Numpad1 = Value(VK_NUMPAD1, getKeyText(VK_NUMPAD1))
  val Numpad2 = Value(VK_NUMPAD2, getKeyText(VK_NUMPAD2))
  val Numpad3 = Value(VK_NUMPAD3, getKeyText(VK_NUMPAD3))
  val Numpad4 = Value(VK_NUMPAD4, getKeyText(VK_NUMPAD4))
  val Numpad5 = Value(VK_NUMPAD5, getKeyText(VK_NUMPAD5))
  val Numpad6 = Value(VK_NUMPAD6, getKeyText(VK_NUMPAD6))
  val Numpad7 = Value(VK_NUMPAD7, getKeyText(VK_NUMPAD7))
  val Numpad8 = Value(VK_NUMPAD8, getKeyText(VK_NUMPAD8))
  val Numpad9 = Value(VK_NUMPAD9, getKeyText(VK_NUMPAD9))
  val Multiply = Value(VK_MULTIPLY, getKeyText(VK_MULTIPLY))
  val Add = Value(VK_ADD, getKeyText(VK_ADD))
  val Separator = Value(VK_SEPARATOR, getKeyText(VK_SEPARATOR))
  val Subtract = Value(VK_SUBTRACT, getKeyText(VK_SUBTRACT))
  val Decimal = Value(VK_DECIMAL, getKeyText(VK_DECIMAL))
  val Divide = Value(VK_DIVIDE, getKeyText(VK_DIVIDE))
  val Delete = Value(VK_DELETE, getKeyText(VK_DELETE))
  val NumLock = Value(VK_NUM_LOCK, getKeyText(VK_NUM_LOCK))
  val ScrollLock = Value(VK_SCROLL_LOCK, getKeyText(VK_SCROLL_LOCK))
  val F1 = Value(VK_F1, getKeyText(VK_F1))
  val F2 = Value(VK_F2, getKeyText(VK_F2))
  val F3 = Value(VK_F3, getKeyText(VK_F3))
  val F4 = Value(VK_F4, getKeyText(VK_F4))
  val F5 = Value(VK_F5, getKeyText(VK_F5))
  val F6 = Value(VK_F6, getKeyText(VK_F6))
  val F7 = Value(VK_F7, getKeyText(VK_F7))
  val F8 = Value(VK_F8, getKeyText(VK_F8))
  val F9 = Value(VK_F9, getKeyText(VK_F9))
  val F10 = Value(VK_F10, getKeyText(VK_F10))
  val F11 = Value(VK_F11, getKeyText(VK_F11))
  val F12 = Value(VK_F12, getKeyText(VK_F12))
  val F13 = Value(VK_F13, getKeyText(VK_F13))
  val F14 = Value(VK_F14, getKeyText(VK_F14))
  val F15 = Value(VK_F15, getKeyText(VK_F15))
  val F16 = Value(VK_F16, getKeyText(VK_F16))
  val F17 = Value(VK_F17, getKeyText(VK_F17))
  val F18 = Value(VK_F18, getKeyText(VK_F18))
  val F19 = Value(VK_F19, getKeyText(VK_F19))
  val F20 = Value(VK_F20, getKeyText(VK_F20))
  val F21 = Value(VK_F21, getKeyText(VK_F21))
  val F22 = Value(VK_F22, getKeyText(VK_F22))
  val F23 = Value(VK_F23, getKeyText(VK_F23))
  val F24 = Value(VK_F24, getKeyText(VK_F24))
  val Printscreen = Value(VK_PRINTSCREEN, getKeyText(VK_PRINTSCREEN))
  val Insert = Value(VK_INSERT, getKeyText(VK_INSERT))
  val Help = Value(VK_HELP, getKeyText(VK_HELP))
  val BackQuote = Value(VK_BACK_QUOTE, getKeyText(VK_BACK_QUOTE))
  val Quote = Value(VK_QUOTE, getKeyText(VK_QUOTE))
  val KpUp = Value(VK_KP_UP, getKeyText(VK_KP_UP))
  val KpDown = Value(VK_KP_DOWN, getKeyText(VK_KP_DOWN))
  val KpLeft = Value(VK_KP_LEFT, getKeyText(VK_KP_LEFT))
  val KpRight = Value(VK_KP_RIGHT, getKeyText(VK_KP_RIGHT))
  val DeadGrave = Value(VK_DEAD_GRAVE, getKeyText(VK_DEAD_GRAVE))
  val DeadAcute = Value(VK_DEAD_ACUTE, getKeyText(VK_DEAD_ACUTE))
  val DeadCircumflex = Value(VK_DEAD_CIRCUMFLEX, getKeyText(VK_DEAD_CIRCUMFLEX))
  val DeadTilde = Value(VK_DEAD_TILDE, getKeyText(VK_DEAD_TILDE))
  val DeadMacron = Value(VK_DEAD_MACRON, getKeyText(VK_DEAD_MACRON))
  val DeadBreve = Value(VK_DEAD_BREVE, getKeyText(VK_DEAD_BREVE))
  val DeadAbovedot = Value(VK_DEAD_ABOVEDOT, getKeyText(VK_DEAD_ABOVEDOT))
  val DeadDiaeresis = Value(VK_DEAD_DIAERESIS, getKeyText(VK_DEAD_DIAERESIS))
  val DeadAbovering = Value(VK_DEAD_ABOVERING, getKeyText(VK_DEAD_ABOVERING))
  val DeadDoubleacute = Value(VK_DEAD_DOUBLEACUTE, getKeyText(VK_DEAD_DOUBLEACUTE))
  val DeadCaron = Value(VK_DEAD_CARON, getKeyText(VK_DEAD_CARON))
  val DeadCedilla = Value(VK_DEAD_CEDILLA, getKeyText(VK_DEAD_CEDILLA))
  val DeadOgonek = Value(VK_DEAD_OGONEK, getKeyText(VK_DEAD_OGONEK))
  val DeadIota = Value(VK_DEAD_IOTA, getKeyText(VK_DEAD_IOTA))
  val DeadVoicedSound = Value(VK_DEAD_VOICED_SOUND, getKeyText(VK_DEAD_VOICED_SOUND))
  val DeadSemivoicedSound = Value(VK_DEAD_SEMIVOICED_SOUND, getKeyText(VK_DEAD_SEMIVOICED_SOUND))
  val Ampersand = Value(VK_AMPERSAND, getKeyText(VK_AMPERSAND))
  val Asterisk = Value(VK_ASTERISK, getKeyText(VK_ASTERISK))
  val Quotedbl = Value(VK_QUOTEDBL, getKeyText(VK_QUOTEDBL))
  val Less = Value(VK_LESS, getKeyText(VK_LESS))
  val Greater = Value(VK_GREATER, getKeyText(VK_GREATER))
  val Braceleft = Value(VK_BRACELEFT, getKeyText(VK_BRACELEFT))
  val Braceright = Value(VK_BRACERIGHT, getKeyText(VK_BRACERIGHT))
  val At = Value(VK_AT, getKeyText(VK_AT))
  val Colon = Value(VK_COLON, getKeyText(VK_COLON))
  val Circumflex = Value(VK_CIRCUMFLEX, getKeyText(VK_CIRCUMFLEX))
  val Dollar = Value(VK_DOLLAR, getKeyText(VK_DOLLAR))
  val EuroSign = Value(VK_EURO_SIGN, getKeyText(VK_EURO_SIGN))
  val ExclamationMark = Value(VK_EXCLAMATION_MARK, getKeyText(VK_EXCLAMATION_MARK))
  val InvertedExclamationMark = Value(VK_INVERTED_EXCLAMATION_MARK, getKeyText(VK_INVERTED_EXCLAMATION_MARK))
  val LeftParenthesis = Value(VK_LEFT_PARENTHESIS, getKeyText(VK_LEFT_PARENTHESIS))
  val NumberSign = Value(VK_NUMBER_SIGN, getKeyText(VK_NUMBER_SIGN))
  val Plus = Value(VK_PLUS, getKeyText(VK_PLUS))
  val RightParenthesis = Value(VK_RIGHT_PARENTHESIS, getKeyText(VK_RIGHT_PARENTHESIS))
  val Underscore = Value(VK_UNDERSCORE, getKeyText(VK_UNDERSCORE))
  val Windows = Value(VK_WINDOWS, getKeyText(VK_WINDOWS))
  val ContextMenu = Value(VK_CONTEXT_MENU, getKeyText(VK_CONTEXT_MENU))
  val Final = Value(VK_FINAL, getKeyText(VK_FINAL))
  val Convert = Value(VK_CONVERT, getKeyText(VK_CONVERT))
  val Nonconvert = Value(VK_NONCONVERT, getKeyText(VK_NONCONVERT))
  val Accept = Value(VK_ACCEPT, getKeyText(VK_ACCEPT))
  val Modechange = Value(VK_MODECHANGE, getKeyText(VK_MODECHANGE))
  val Kana = Value(VK_KANA, getKeyText(VK_KANA))
  val Kanji = Value(VK_KANJI, getKeyText(VK_KANJI))
  val Alphanumeric = Value(VK_ALPHANUMERIC, getKeyText(VK_ALPHANUMERIC))
  val Katakana = Value(VK_KATAKANA, getKeyText(VK_KATAKANA))
  val Hiragana = Value(VK_HIRAGANA, getKeyText(VK_HIRAGANA))
  val FullWidth = Value(VK_FULL_WIDTH, getKeyText(VK_FULL_WIDTH))
  val HalfWidth = Value(VK_HALF_WIDTH, getKeyText(VK_HALF_WIDTH))
  val RomanCharacters = Value(VK_ROMAN_CHARACTERS, getKeyText(VK_ROMAN_CHARACTERS))
  val AllCandidates = Value(VK_ALL_CANDIDATES, getKeyText(VK_ALL_CANDIDATES))
  val PreviousCandidate = Value(VK_PREVIOUS_CANDIDATE, getKeyText(VK_PREVIOUS_CANDIDATE))
  val CodeInput = Value(VK_CODE_INPUT, getKeyText(VK_CODE_INPUT))
  val JapaneseKatakana = Value(VK_JAPANESE_KATAKANA, getKeyText(VK_JAPANESE_KATAKANA))
  val JapaneseHiragana = Value(VK_JAPANESE_HIRAGANA, getKeyText(VK_JAPANESE_HIRAGANA))
  val JapaneseRoman = Value(VK_JAPANESE_ROMAN, getKeyText(VK_JAPANESE_ROMAN))
  val KanaLock = Value(VK_KANA_LOCK, getKeyText(VK_KANA_LOCK))
  val InputMethodOnOff = Value(VK_INPUT_METHOD_ON_OFF, getKeyText(VK_INPUT_METHOD_ON_OFF))
  val Cut = Value(VK_CUT, getKeyText(VK_CUT))
  val Copy = Value(VK_COPY, getKeyText(VK_COPY))
  val Paste = Value(VK_PASTE, getKeyText(VK_PASTE))
  val Undo = Value(VK_UNDO, getKeyText(VK_UNDO))
  val Again = Value(VK_AGAIN, getKeyText(VK_AGAIN))
  val Find = Value(VK_FIND, getKeyText(VK_FIND))
  val Props = Value(VK_PROPS, getKeyText(VK_PROPS))
  val Stop = Value(VK_STOP, getKeyText(VK_STOP))
  val Compose = Value(VK_COMPOSE, getKeyText(VK_COMPOSE))
  val Begin = Value(VK_BEGIN, getKeyText(VK_BEGIN))
  val Undefined = Value(VK_UNDEFINED, getKeyText(VK_UNDEFINED))
}
