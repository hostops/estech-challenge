import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISystemDeviceData } from 'app/shared/model/system-device-data.model';

type EntityResponseType = HttpResponse<ISystemDeviceData>;
type EntityArrayResponseType = HttpResponse<ISystemDeviceData[]>;

@Injectable({ providedIn: 'root' })
export class SystemDeviceDataService {
    public resourceUrl = SERVER_API_URL + 'api/system-device-data';

    constructor(protected http: HttpClient) {}

    create(systemDeviceData: ISystemDeviceData): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(systemDeviceData);
        return this.http
            .post<ISystemDeviceData>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(systemDeviceData: ISystemDeviceData): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(systemDeviceData);
        return this.http
            .put<ISystemDeviceData>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISystemDeviceData>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISystemDeviceData[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(systemDeviceData: ISystemDeviceData): ISystemDeviceData {
        const copy: ISystemDeviceData = Object.assign({}, systemDeviceData, {
            timestamp:
                systemDeviceData.timestamp != null && systemDeviceData.timestamp.isValid() ? systemDeviceData.timestamp.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((systemDeviceData: ISystemDeviceData) => {
                systemDeviceData.timestamp = systemDeviceData.timestamp != null ? moment(systemDeviceData.timestamp) : null;
            });
        }
        return res;
    }
}
