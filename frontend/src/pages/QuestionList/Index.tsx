/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Loader from 'react-loader-spinner';
import { useSelector } from 'react-redux';
import { useTranslation } from 'react-i18next';
import InfiniteScroll from 'react-infinite-scroll-component';
import SubHeader from '../../components/SubHeader';
import Header from '../../components/Header';
import Banner from '../../components/Banner';
import { bannerImageMate, noAnswer } from '../../assets';
import QuestionGroup from './components/QuestionGroup';
import Filters from './components/Filters';
import { RootState } from '../../modules';
import { getQuestions, getTotalSize } from '../../api/question';
import { ResponseQuestionType } from '../../entity';

const QuestionList: React.FC = () => {
  const { t } = useTranslation();
  const [isFiltered, setIsFiltered] = useState<boolean>(false);
  const [listType] = useState<number>(1); // 1- 등록날짜순(내림차순), 2- 마감날짜순(오름차순), 3-포인트순(내림차순)
  const [limit] = useState<number>(5); // 한번에 몇개의 질문을 보여줄 것인지
  const [offset, setOffset] = useState<number>(0); // 페이지 넘버, 0부터 시작
  const [totalNum, setTotalNum] = useState<number>(0);
  const [hasMore, setHasMore] = useState<boolean>(true);
  const [questions, setQuestions] = useState<ResponseQuestionType[]>([]);
  const loginUserId: number = useSelector((state: RootState) => state.member).member.memberId;

  // 질문 로드
  useEffect(() => {
    fetchQuestionNumber();
  });

  // 질문 불러오기
  const fetchQuestionNumber = async () => {
    const totalNum = await getTotalSize(`questions/${listType}/${offset}/${limit}`);
    setTotalNum(totalNum);

    if (totalNum === 0) {
      setHasMore(false);
    }

    if (limit * offset < totalNum) {
      setOffset(offset + 1);
      fetchQuestions();
    } else {
      setHasMore(false); // 더이상 불러올 데이터가 없을 때
    }
  };

  const fetchQuestions = async () => {
    if (hasMore && !isFiltered) {
      const response = await getQuestions(`questions/${listType}/${offset}/${limit}`);
      const questionList = [...questions, ...response];
      setQuestions(questionList);
    }
  };

  const handleMyQuestion = () => {
    setIsFiltered(!isFiltered);
  };

  const bannerData = {
    img: bannerImageMate,
    title: t('mate'),
    description: t('list_description3'),
  };

  return (
    <HomeContainer>
      <SubHeader />
      <Header />
      <Banner banner={bannerData} />
      <Filters handleMyQuestion={handleMyQuestion} id={loginUserId} />

      {/* question group */}
      {totalNum > 0 ? (
        <InfiniteScroll
          dataLength={offset}
          next={fetchQuestions}
          hasMore={hasMore}
          loader={<Loader type="Puff" color="#00BFFF" height={100} width={100} timeout={3000} />}
        >
          <QuestionGroup questions={questions} isFiltered={isFiltered} id={loginUserId} />
        </InfiniteScroll>
      ) : (
        <NoAnswer>
          <NoAnswerImage src={noAnswer} alt="no answer" />
          {t('list_msg_empty')}
        </NoAnswer>
      )}
    </HomeContainer>
  );
};

const HomeContainer = styled.div`
  overflow: hidden;
  text-align: center;
  background-color: ${({ theme }) => theme.colors.white};
  color: ${({ theme }) => theme.colors.black};
  font-size: ${({ theme }) => theme.fontSizes.h1};
`;

const NoAnswer = styled.div`
  width: 100%;
  display: flex;
  margin: 100px auto;
  flex-direction: column;
  align-items: center;
  font-family: 'Kirang Haerang', cursive;
  font-weight: normal;
  font-size: 72px;
`;
const NoAnswerImage = styled.img`
  width: 388px;
  height: 388px;
  margin-bottom: 10px;
`;
export default QuestionList;
