/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.util;

import java.io.*;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public interface Persistent {
    void resurrect( int recordNumber, RecordStore resurrectStore ) throws IOException, RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException;
}