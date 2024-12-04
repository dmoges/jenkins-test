import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row, UncontrolledTooltip } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './professor.reducer';

export const ProfessorDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const professorEntity = useAppSelector(state => state.professor.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="professorDetailsHeading">
          <Translate contentKey="jenkinsTestApp.professor.detail.title">Professor</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{professorEntity.id}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="jenkinsTestApp.professor.firstName">First Name</Translate>
            </span>
            <UncontrolledTooltip target="firstName">
              <Translate contentKey="jenkinsTestApp.professor.help.firstName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{professorEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="jenkinsTestApp.professor.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{professorEntity.lastName}</dd>
          <dt>
            <span id="telephone">
              <Translate contentKey="jenkinsTestApp.professor.telephone">Telephone</Translate>
            </span>
          </dt>
          <dd>{professorEntity.telephone}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="jenkinsTestApp.professor.email">Email</Translate>
            </span>
          </dt>
          <dd>{professorEntity.email}</dd>
          <dt>
            <span id="hireDate">
              <Translate contentKey="jenkinsTestApp.professor.hireDate">Hire Date</Translate>
            </span>
          </dt>
          <dd>
            {professorEntity.hireDate ? <TextFormat value={professorEntity.hireDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="jenkinsTestApp.professor.speciality">Speciality</Translate>
          </dt>
          <dd>{professorEntity.speciality ? professorEntity.speciality.label : ''}</dd>
        </dl>
        <Button tag={Link} to="/professor" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/professor/${professorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProfessorDetail;
