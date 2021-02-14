import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISystemDeviceType } from 'app/shared/model/system-device-type.model';

type EntityResponseType = HttpResponse<ISystemDeviceType>;
type EntityArrayResponseType = HttpResponse<ISystemDeviceType[]>;

@Injectable({ providedIn: 'root' })
export class SystemDeviceTypeService {
    public resourceUrl = SERVER_API_URL + 'api/system-device-types';

    constructor(protected http: HttpClient) {}

    create(systemDeviceType: ISystemDeviceType): Observable<EntityResponseType> {
        return this.http.post<ISystemDeviceType>(this.resourceUrl, systemDeviceType, { observe: 'response' });
    }

    update(systemDeviceType: ISystemDeviceType): Observable<EntityResponseType> {
        return this.http.put<ISystemDeviceType>(this.resourceUrl, systemDeviceType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISystemDeviceType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISystemDeviceType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
