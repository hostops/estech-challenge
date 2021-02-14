import { ISystemDevice } from 'app/shared/model/system-device.model';
import { IUser } from 'app/core/user/user.model';

export interface ISystemUnit {
    id?: number;
    name?: string;
    description?: string;
    systemDevices?: ISystemDevice[];
    users?: IUser[];
}

export class SystemUnit implements ISystemUnit {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public systemDevices?: ISystemDevice[],
        public users?: IUser[]
    ) {}
}
