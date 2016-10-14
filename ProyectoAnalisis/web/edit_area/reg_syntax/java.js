editAreaLoader.load_syntax["java"] = {
    'DISPLAY_NAME': 'Java'
    , 'COMMENT_SINGLE': {1: '//', 2: '@'}
    , 'COMMENT_MULTI': {'/*': '*/'}
    , 'QUOTEMARKS': {1: "'", 2: '"'}
    , 'KEYWORD_CASE_SENSITIVE': true
    , 'KEYWORDS': {
        'constants': [
            'null', 'false', 'true'
        ]
        , 'types': [
            'String', 'int', 'short', 'long', 'char', 'double', 'byte',
            'float', 'static', 'void', 'private', 'boolean', 'protected',
            'public', 'const', 'class', 'final', 'abstract', 'volatile',
            'enum', 'transient', 'interface', 'return', 'procedure', 'function'
        ]
        , 'statements': [
            'if', 'then', 'for', 'to', 'while',
            'else', 'elif', 'end', 'do',
            'until', 'begin', 'repet', 'to', 'call'
        ]
     
    }
    , 'OPERATORS': [
        'and', 'not', 'mod', 'not', 'or', '+', '-', '/', '*', '=', '<', '>'
    ]
    , 'DELIMITERS': [
        '(', ')', '[', ']', '{', '}'
    ]
    , 'REGEXPS': {
        'precompiler': {
            'search': '()(#[^\r\n]*)()'
            , 'class': 'precompiler'
            , 'modifiers': 'g'
            , 'execute': 'before'
        }
    }
    , 'STYLES': {
        'COMMENTS': 'color: #AAAAAA;'
        , 'QUOTESMARKS': 'color: #6381F8;'
        , 'KEYWORDS': {
            'constants': 'color: #EE0000;'
            , 'types': 'color: #0000EE;'
            , 'statements': 'color: #60CA00;'
            , 'keywords': 'color: #48BDDF;'
        }
        , 'OPERATORS': 'color: #FF00FF;'
        , 'DELIMITERS': 'color: #0038E1;'
        , 'REGEXPS': {
            'precompiler': 'color: #009900;'
            , 'precompilerstring': 'color: #994400;'
        }
    }
};

