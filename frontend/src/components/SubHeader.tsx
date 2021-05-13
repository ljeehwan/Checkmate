/* eslint-disable react/button-has-type */
import React, { ReactElement, useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';
import { useTranslation } from 'react-i18next';
import { RootState } from '../modules';
import { MemberType } from '../entity';
import { logout } from '../modules/member';

const SubHeader = (): ReactElement => {
  const [member, setMember] = useState<MemberType | null>(useSelector((state: RootState) => state.member.member));
  const [isMember, setIsMember] = useState<boolean>(false);
  const [profileLink, setProfileLink] = useState<string>('');

  const dispatch = useDispatch();
  const router = useHistory();
  const { t, i18n } = useTranslation();

  useEffect(() => {
    const userLanguage = localStorage.getItem('language') || window.navigator.language || 'en';

    if (member === null) {
      setIsMember(false);
      i18n.changeLanguage(userLanguage);
    } else if (member.memberId > 0) {
      setIsMember(true);
      setProfileLink(`/profile/${member.memberId}`);
      i18n.changeLanguage(member.memberNativeLang);
    }
  }, [member, i18n]);

  // 로그아웃 액션 호출
  const onClickLogoutBtn = () => {
    dispatch(logout());
    setMember(null);
    router.push('/');
  };

  // 언어 변경
  const handleChangeLanguage = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const lang = e.target.value;
    i18n.changeLanguage(lang);
    localStorage.setItem('language', lang);
  };

  return (
    <SubHeaderContainer>
      <Select onChange={handleChangeLanguage}>
        <option value="" selected disabled>
          {t('language')}
        </option>
        <option value="ko">한국어</option>
        <option value="en">English</option>
        <option value="zh">中文</option>
      </Select>

      {isMember ? (
        <>
          <StyledLink to={profileLink}>{t('mypage')}</StyledLink>
          <LogoutBtn onClick={onClickLogoutBtn}>{t('logout')}</LogoutBtn>
        </>
      ) : (
        <>
          <StyledLink to="/login">{t('login')}</StyledLink>
          <StyledLink to="/register">{t('register')}</StyledLink>
        </>
      )}
    </SubHeaderContainer>
  );
};

// SubHeader style
const SubHeaderContainer = styled.div`
  width: 100%;
  height: 58px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  padding: 16px 20px;
  background-color: ${({ theme }) => theme.colors.primary};
`;

const Select = styled.select`
  width: 100px;
  color: ${({ theme }) => theme.colors.white};
  font-size: ${({ theme }) => theme.fontSizes.body};
  font-weight: normal;
  border: none;
  background-color: transparent;
  outline: none;
`;

const StyledLink = styled(Link)`
  text-decoration: none;
  margin-left: 30px;
  color: ${({ theme }) => theme.colors.white};
  font-size: ${({ theme }) => theme.fontSizes.body};
  font-weight: normal;
  cursor: pointer;
`;

const LogoutBtn = styled.a`
  text-decoration: none;
  margin-left: 30px;
  color: ${({ theme }) => theme.colors.white};
  font-size: ${({ theme }) => theme.fontSizes.body};
  font-weight: normal;
  cursor: pointer;
`;

export default SubHeader;
