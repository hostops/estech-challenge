import { ISystemDeviceData } from 'app/shared/model/system-device-data.model';
import { ISystemDevice } from 'app/shared/model/system-device.model';

export interface ISystemDevice {
    id?: number;
    serialNumber?: string;
    deviceConfiguration?: string;
    data?: ISystemDeviceData[];
    typeName?: string;
    typeId?: number;
    connectionsUpcomings?: ISystemDevice[];
    systemUnitName?: string;
    systemUnitId?: number;
    connectionsOutgoings?: ISystemDevice[];
}

export class SystemDevice implements ISystemDevice {
    constructor(
        public id?: number,
        public serialNumber?: string,
        public deviceConfiguration?: string,
        public data?: ISystemDeviceData[],
        public typeName?: string,
        public typeId?: number,
        public connectionsUpcomings?: ISystemDevice[],
        public systemUnitName?: string,
        public systemUnitId?: number,
        public connectionsOutgoings?: ISystemDevice[]
    ) {}
}
