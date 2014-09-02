/*================================================================================
Copyright (c) 2008 VMware, Inc. All Rights Reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice, 
this list of conditions and the following disclaimer in the documentation 
and/or other materials provided with the distribution.

* Neither the name of VMware, Inc. nor the names of its contributors may be used
to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL VMWARE, INC. OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
================================================================================*/

package com.vmware.vim25.mo;

import java.rmi.RemoteException;

import com.vmware.vim25.FileFault;
import com.vmware.vim25.InvalidDatastore;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.UserNotFound;

/**
 * The managed object class corresponding to the one defined in VI SDK API reference.
 * @author Steve JIN (http://www.doublecloud.org)
 */

public class FileManager extends ManagedObject 
{

	public FileManager(ServerConnection serverConnection, ManagedObjectReference mor) 
	{
		super(serverConnection, mor);
	}

	/**
	 * @since 4.0
	 */
	public void changeOwner(String name, Datacenter datacenter, String owner) throws InvalidDatastore, FileFault, UserNotFound, RuntimeFault, RemoteException
	{
		getVimService().changeOwner(getMOR(), name, 
				datacenter==null?null:datacenter.getMOR(), owner);
	}
	
	public Task copyDatastoreFile_Task(String sourceName, Datacenter sourceDatacenter, 
			String destinationName, Datacenter destinationDatacenter, boolean force) throws FileFault, InvalidDatastore, RuntimeFault, RemoteException 
	{
		ManagedObjectReference taskMor = getVimService().copyDatastoreFile_Task(getMOR(), 
				sourceName, sourceDatacenter==null? null : sourceDatacenter.getMOR(), 
				destinationName, sourceDatacenter==null? null : destinationDatacenter.getMOR(), force);
		return new Task(getServerConnection(), taskMor);
	}

	public Task deleteDatastoreFile_Task(String name, Datacenter datacenter) throws FileFault, InvalidDatastore, RuntimeFault, RemoteException 
	{
		ManagedObjectReference taskMor = getVimService().deleteDatastoreFile_Task(getMOR(), 
				name, datacenter==null? null : datacenter.getMOR());
		return new Task(getServerConnection(), taskMor);
	}
	
	public void makeDirectory(String name, Datacenter datacenter, boolean createParentDirectories) throws FileFault, InvalidDatastore, RuntimeFault, RemoteException 
	{
		getVimService().makeDirectory(getMOR(), name, datacenter==null? null : datacenter.getMOR(), createParentDirectories);
	}

	public Task moveDatastoreFile_Task(String sourceName, Datacenter sourceDatacenter, 
			String destinationName, Datacenter destinationDatacenter, boolean force) throws FileFault, InvalidDatastore, RuntimeFault, RemoteException 
	{
		ManagedObjectReference taskMor = getVimService().moveDatastoreFile_Task(getMOR(), 
				sourceName, sourceDatacenter==null? null : sourceDatacenter.getMOR(), 
				destinationName, destinationDatacenter==null? null : destinationDatacenter.getMOR(), force);
		return new Task(getServerConnection(), taskMor);
	}

}
