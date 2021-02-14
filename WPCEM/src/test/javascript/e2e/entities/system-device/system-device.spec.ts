/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SystemDeviceComponentsPage, SystemDeviceDeleteDialog, SystemDeviceUpdatePage } from './system-device.page-object';

const expect = chai.expect;

describe('SystemDevice e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let systemDeviceUpdatePage: SystemDeviceUpdatePage;
    let systemDeviceComponentsPage: SystemDeviceComponentsPage;
    let systemDeviceDeleteDialog: SystemDeviceDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SystemDevices', async () => {
        await navBarPage.goToEntity('system-device');
        systemDeviceComponentsPage = new SystemDeviceComponentsPage();
        await browser.wait(ec.visibilityOf(systemDeviceComponentsPage.title), 5000);
        expect(await systemDeviceComponentsPage.getTitle()).to.eq('wpcemApp.systemDevice.home.title');
    });

    it('should load create SystemDevice page', async () => {
        await systemDeviceComponentsPage.clickOnCreateButton();
        systemDeviceUpdatePage = new SystemDeviceUpdatePage();
        expect(await systemDeviceUpdatePage.getPageTitle()).to.eq('wpcemApp.systemDevice.home.createOrEditLabel');
        await systemDeviceUpdatePage.cancel();
    });

    it('should create and save SystemDevices', async () => {
        const nbButtonsBeforeCreate = await systemDeviceComponentsPage.countDeleteButtons();

        await systemDeviceComponentsPage.clickOnCreateButton();
        await promise.all([
            systemDeviceUpdatePage.setSerialNumberInput('serialNumber'),
            systemDeviceUpdatePage.setDeviceConfigurationInput('deviceConfiguration'),
            systemDeviceUpdatePage.typeSelectLastOption(),
            // systemDeviceUpdatePage.connectionsUpcomingSelectLastOption(),
            systemDeviceUpdatePage.systemUnitSelectLastOption()
        ]);
        expect(await systemDeviceUpdatePage.getSerialNumberInput()).to.eq('serialNumber');
        expect(await systemDeviceUpdatePage.getDeviceConfigurationInput()).to.eq('deviceConfiguration');
        await systemDeviceUpdatePage.save();
        expect(await systemDeviceUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await systemDeviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SystemDevice', async () => {
        const nbButtonsBeforeDelete = await systemDeviceComponentsPage.countDeleteButtons();
        await systemDeviceComponentsPage.clickOnLastDeleteButton();

        systemDeviceDeleteDialog = new SystemDeviceDeleteDialog();
        expect(await systemDeviceDeleteDialog.getDialogTitle()).to.eq('wpcemApp.systemDevice.delete.question');
        await systemDeviceDeleteDialog.clickOnConfirmButton();

        expect(await systemDeviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
