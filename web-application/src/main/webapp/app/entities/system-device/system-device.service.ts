import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISystemDevice } from 'app/shared/model/system-device.model';

type EntityResponseType = HttpResponse<ISystemDevice>;
type EntityArrayResponseType = HttpResponse<ISystemDevice[]>;

@Injectable({ providedIn: 'root' })
export class SystemDeviceService {
    public resourceUrl = SERVER_API_URL + 'api/system-devices';

    constructor(protected http: HttpClient) {}

    create(systemDevice: ISystemDevice): Observable<EntityResponseType> {
        return this.http.post<ISystemDevice>(this.resourceUrl, systemDevice, { observe: 'response' });
    }

    update(systemDevice: ISystemDevice): Observable<EntityResponseType> {
        return this.http.put<ISystemDevice>(this.resourceUrl, systemDevice, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISystemDevice>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISystemDevice[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
