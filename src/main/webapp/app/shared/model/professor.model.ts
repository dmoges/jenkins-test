import dayjs from 'dayjs';
import { ISpeciality } from 'app/shared/model/speciality.model';

export interface IProfessor {
  id?: number;
  firstName?: string;
  lastName?: string;
  telephone?: string;
  email?: string;
  hireDate?: dayjs.Dayjs;
  speciality?: ISpeciality | null;
}

export const defaultValue: Readonly<IProfessor> = {};
