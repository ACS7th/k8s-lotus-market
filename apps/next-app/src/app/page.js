'use client';

import React, { useState } from 'react';
import {
  AppLayout,
  BreadcrumbGroup,
  Container,
  ContentLayout,
  Flashbar,
  Header,
  HelpPanel,
  Link,
  SideNavigation,
  SplitPanel,
  SpaceBetween,
} from '@cloudscape-design/components';
import { I18nProvider } from '@cloudscape-design/components/i18n';
import messages from '@cloudscape-design/components/i18n/messages/all.en';
import PostForm from '../components/PostForm';
import PostList from '../components/PostList';

const LOCALE = 'ko';

export default function HomePage() {
  const [navigationOpen, setNavigationOpen] = useState(true);

  return (
    <I18nProvider locale={LOCALE} messages={[]}>
      <AppLayout
        breadcrumbs={
          <BreadcrumbGroup
            items={[
              { text: 'Home', href: '#' },
              { text: '디지털 제품', href: '#' },
            ]}
          />
        }
        toolsHide={true}
        navigationOpen={navigationOpen}
        onNavigationChange={(e)=> setNavigationOpen(e.detail.open)}
        navigation={
          <SideNavigation
            header={{
              href: '/',
              text: '분류',
            }}
            items={[
              { type: 'link', text: `디지털`, href: `/` },
              { type: 'link', text: `생활용품`, href: `/` },
              { type: 'link', text: `의류`, href: `/` },
            ]}
          />
        }
        content={
          <ContentLayout
            header={
              <Header variant="h1" info={<Link variant="info">Info</Link>}>
                디지털 제품
              </Header>
            }
          >
              <SpaceBetween size="l">
                <PostForm />
                <PostList />
              </SpaceBetween>
          </ContentLayout>
        }
      />
    </I18nProvider>
  );
}
