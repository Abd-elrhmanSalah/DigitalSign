

package com.dataserve.digitalsign.exception;


public class ApplicationException extends BaseException {

    private static final long serialVersionUID = -741215074424755266L;


    public ApplicationException(StatusResponse status) {
        super(status);
    }

}
