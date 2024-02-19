# Структура файла
## _**HEADER**_
 - Format:
   - Amount of records total: 
     len of file = headerLen + recordLen * recAmount  
     `int` -> `i`
   - Record format:
     16 bytes as `ascii string` -> `16s`
 - Total: `i28s` -> _20 bytes_

## _**RECORD**_
- Format:
    - Id: `int` -> `i`
    - Date and time of arriving:  
      `"%Y-%m-%d"` as `ascii string` -> `8s`
    - Room ID -> _5 bytes_ as `ascii string` -> `5s`
    - Months amount as _2 bytes_ `unsigned integer` -> `h`
- Total: `i12s11si` -> 20 bytes 