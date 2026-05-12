; ENABLES 32-BIT REGISTERS
.386

DATA SEGMENT USE16
    ; MENU UI
    MENU_MSG    DB 13, 10, "=============================="
                DB 13, 10, "        NUMBER CONVERTER      "
                DB 13, 10, "=============================="
                DB 13, 10, "1. Decimal to Binary"
                DB 13, 10, "2. Binary to Decimal"
                DB 13, 10, "3. Exit"
                DB 13, 10, "Choice: $"
    
    DEC_PROMPT  DB 13, 10, "Enter Decimal (Max 4294967295): $"
    BIN_PROMPT  DB 13, 10, "Enter Binary (Up to 32-bits): $"

    RES_MSG     DB 13, 10, "Result: $"
    PAUSE_MSG   DB 13, 10, "Press any key to continue...$"
    EXIT_MSG    DB 13, 10, "Program Terminated.$"

    ERR_DEC     DB 13,10,"Invalid decimal input!$"
    ERR_BIN     DB 13,10,"Invalid binary input!$"

DATA ENDS

; ASSUMES CODE SEGMENT AS CODE / DATA SEGMENT AS DATA
CODE SEGMENT USE16
    ASSUME CS:CODE, DS:DATA

; INITIALIZATION
START:

    MOV AX, DATA
    MOV DS, AX

; MENU INPUT CHECK
MAIN_MENU:

    LEA DX, MENU_MSG
    MOV AH, 09H
    INT 21H

    MOV AH, 01H
    INT 21H
    
    CMP AL, '1'
    JE  JUMP_DEC

    CMP AL, '2'
    JE  JUMP_BIN

    CMP AL, '3'
    JE  JUMP_FINISH

    JMP MAIN_MENU

; JUMP BRIDGES
JUMP_DEC:      JMP DEC_TO_BIN
JUMP_BIN:      JMP BIN_TO_DEC
JUMP_FINISH:   JMP FINISH


; DECIMAL TO BINARY
DEC_TO_BIN:

    LEA DX, DEC_PROMPT
    MOV AH, 09H
    INT 21H

    MOV EBX, 0

; READS THE INPUT AND EXTRACT THEM IN CHARACTERS
READ_DEC:

    MOV AH, 01H
    INT 21H

    CMP AL, 13 ; 13 IS WHEN USER HIT ENTER
    JE  START_BIN_OUT

    ; CHECKS IF INPUT IS BETWEEN 0-9
    CMP AL, '0'
    JB INVALID_DEC

    CMP AL, '9'
    JA INVALID_DEC

    SUB AL, 30H

    MOVZX EAX, AL
    PUSH EAX

    MOV EAX, EBX
    MOV ECX, 10

    MUL ECX
    POP EBX
    ADD EBX, EAX
    JMP READ_DEC

; VALIDATION
INVALID_DEC:

    LEA DX, ERR_DEC
    MOV AH, 09H
    INT 21H

    JMP READ_DEC

; prints RESULT: and prepares for loop
START_BIN_OUT:

    LEA DX, RES_MSG
    MOV AH, 09H
    INT 21H

    MOV CX, 32
    MOV SI, 0

; only starts printing if the first 1 is found.
PRINT_BIT:

    SHL EBX, 1
    JNC CHECK_FLAG

    MOV SI, 1
    MOV DL, '1'

    JMP DO_PRINT


CHECK_FLAG:

    CMP SI, 0
    JE  SKIP_BIT

    MOV DL, '0'

; PRINTER
DO_PRINT:

    MOV AH, 02H
    INT 21H


SKIP_BIT:

    LOOP PRINT_BIT


    CMP SI, 0
    JNE PAUSE_SCREEN

    MOV DL, '0'
    MOV AH, 02H
    INT 21H

    JMP PAUSE_SCREEN


; BINARY TO DECIMAL
BIN_TO_DEC:

    LEA DX, BIN_PROMPT
    MOV AH, 09H
    INT 21H

    MOV EBX, 0

; READ IN THE INPUT FROM USER AND STORES IN EBX
READ_BIN:

    MOV AH, 01H
    INT 21H
    CMP AL, 13
    JE  START_DEC_OUT
    
    ; VALIDATION only allows 0 and 1
    CMP AL, '0'
    JE VALID_BIN
    CMP AL, '1'
    JE VALID_BIN
    JMP INVALID_BIN

; VALIDATION
VALID_BIN:

    SUB AL, 30H
    SHL EBX, 1
    MOVZX EAX, AL
    OR EBX, EAX
    JMP READ_BIN


INVALID_BIN:

    LEA DX, ERR_BIN
    MOV AH, 09H
    INT 21H

    JMP READ_BIN

; PREPARES FOR DIVISION
START_DEC_OUT:

    LEA DX, RES_MSG
    MOV AH, 09H
    INT 21H

    MOV EAX, EBX
    MOV ECX, 10
    MOV DI, 0

; REPEATEDLY DIVIDES BY 10 and EXTRACTS EACH DECIMAL DIGIT
DIV_LOOP:

    MOV EDX, 0
    DIV ECX
    PUSH DX
    INC DI

    CMP EAX, 0
    JNE DIV_LOOP

; Pops the digits from the stack and prints them in correct order.
PRINT_DIGITS:

    POP DX
    ADD DL, 30H
    MOV AH, 02H
    INT 21H
    DEC DI

    JNZ PRINT_DIGITS
    JMP PAUSE_SCREEN

; PAUSE
PAUSE_SCREEN:

    LEA DX, PAUSE_MSG
    MOV AH, 09H
    INT 21H

    MOV AH, 07H
    INT 21H

    JMP MAIN_MENU

; EXIT
FINISH:

    LEA DX, EXIT_MSG
    MOV AH, 09H
    INT 21H

    MOV AH, 4CH
    INT 21H


CODE ENDS
END START