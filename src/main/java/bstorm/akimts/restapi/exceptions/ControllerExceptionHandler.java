package bstorm.akimts.restapi.exceptions;


import bstorm.akimts.restapi.controller.handled.VoitureController;
import bstorm.akimts.restapi.exceptions.models.ErrorDTO;
import bstorm.akimts.restapi.exceptions.models.VoitureNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

// pour tous les controllers d'un package
//@RestControllerAdvice(basePackages = "bstorm.akimts.restapi.controller.handled")
// pour un/des controller(s) particuliers
//@RestControllerAdvice(assignableTypes = VoitureController.class)
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<ErrorDTO> handle(RuntimeException ex){
//        return ResponseEntity.badRequest()
//                .body( new ErrorDTO(ex.getMessage()) );
//    }
//
//    @ExceptionHandler(VoitureNotFoundException.class)
//    public ResponseEntity<ErrorDTO> handle(VoitureNotFoundException ex) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new ErrorDTO(ex.getMessage()));
//    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handle(Throwable ex, HttpServletRequest request/*pour recup info de requete*/){

        if(ex.getMessage() != null){

            if( ex instanceof IllegalArgumentException ){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorDTO(ex.getMessage()));
            }
            if( ex instanceof VoitureNotFoundException ){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDTO(ex.getMessage()));
            }
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO("Une erreur inconnue s'est produite"));
    }


    // gestion générale des 404
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<ErrorDTO> handle(NoHandlerFoundException ex){
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new ErrorDTO(ex.getMessage()));
//
//    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO(ex.getMessage()));
    }

}
