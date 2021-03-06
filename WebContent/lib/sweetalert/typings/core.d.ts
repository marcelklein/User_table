import { ActionOptions, SwalState } from 'sweetalert/typings/modules/state';
import { SwalOptions } from 'sweetalert/typings/modules/options';
export declare type SwalParams = (string | Partial<SwalOptions>)[];
export interface SweetAlert {
	(...params: SwalParams): Promise<any>;
	close?(namespace?: string): void;
	getState?(): SwalState;
	setActionValue?(opts: string | ActionOptions): void;
	stopLoading?(): void;
	setDefaults?(opts: object): void;
}
declare const swal: SweetAlert;
export default swal;
