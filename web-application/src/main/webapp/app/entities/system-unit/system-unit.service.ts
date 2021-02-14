import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISystemUnit } from 'app/shared/model/system-unit.model';

type EntityResponseType = HttpResponse<ISystemUnit>;
type EntityArrayResponseType = HttpResponse<ISystemUnit[]>;

@Injectable({ providedIn: 'root' })
export class SystemUnitService {
    public resourceUrl = SERVER_API_URL + 'api/system-units';

    constructor(protected http: HttpClient) {}

    create(systemUnit: ISystemUnit): Observable<EntityResponseType> {
        return this.http.post<ISystemUnit>(this.resourceUrl, systemUnit, { observe: 'response' });
    }

    update(systemUnit: ISystemUnit): Observable<EntityResponseType> {
        return this.http.put<ISystemUnit>(this.resourceUrl, systemUnit, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISystemUnit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISystemUnit[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
