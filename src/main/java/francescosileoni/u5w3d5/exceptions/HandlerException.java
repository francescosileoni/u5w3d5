package francescosileoni.u5w3d5.exceptions;




import francescosileoni.u5w3d5.payloads.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle404(NotFoundException ex){
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle400(BadRequestException ex){
        if(ex.getErrorsList()!=null){
            String msg=ex.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining("/ "));
            return new ErrorResponse(msg,LocalDateTime.now());
        }else{
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        }
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericERR(Exception ex){
        ex.printStackTrace();
        return new ErrorResponse("Problema LATO SERVER: WORKING ON IT!",LocalDateTime.now());
    }
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle401(UnAuthorizedException ex){
        return new ErrorResponse("NON AUTORIZZATO",LocalDateTime.now());
    }

}

