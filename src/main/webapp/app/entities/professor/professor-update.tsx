import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row, UncontrolledTooltip } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getSpecialities } from 'app/entities/speciality/speciality.reducer';
import { createEntity, getEntity, reset, updateEntity } from './professor.reducer';

export const ProfessorUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const specialities = useAppSelector(state => state.speciality.entities);
  const professorEntity = useAppSelector(state => state.professor.entity);
  const loading = useAppSelector(state => state.professor.loading);
  const updating = useAppSelector(state => state.professor.updating);
  const updateSuccess = useAppSelector(state => state.professor.updateSuccess);

  const handleClose = () => {
    navigate(`/professor${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getSpecialities({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...professorEntity,
      ...values,
      speciality: specialities.find(it => it.id.toString() === values.speciality?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...professorEntity,
          speciality: professorEntity?.speciality?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jenkinsTestApp.professor.home.createOrEditLabel" data-cy="ProfessorCreateUpdateHeading">
            <Translate contentKey="jenkinsTestApp.professor.home.createOrEditLabel">Create or edit a Professor</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="professor-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('jenkinsTestApp.professor.firstName')}
                id="professor-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="firstNameLabel">
                <Translate contentKey="jenkinsTestApp.professor.help.firstName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('jenkinsTestApp.professor.lastName')}
                id="professor-lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('jenkinsTestApp.professor.telephone')}
                id="professor-telephone"
                name="telephone"
                data-cy="telephone"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('jenkinsTestApp.professor.email')}
                id="professor-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  pattern: {
                    value: /^[^@\s]+@[^@\s]+@\.[^@\s]+$/,
                    message: translate('entity.validation.pattern', { pattern: '^[^@\\s]+@[^@\\s]+@\\.[^@\\s]+$' }),
                  },
                }}
              />
              <ValidatedField
                label={translate('jenkinsTestApp.professor.hireDate')}
                id="professor-hireDate"
                name="hireDate"
                data-cy="hireDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="professor-speciality"
                name="speciality"
                data-cy="speciality"
                label={translate('jenkinsTestApp.professor.speciality')}
                type="select"
              >
                <option value="" key="0" />
                {specialities
                  ? specialities.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.label}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/professor" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ProfessorUpdate;
